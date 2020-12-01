

package com.example.qrcodehune.base.FragmentBase

import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.qrcodehune.R
import com.example.qrcodehune.api.APIClient
import com.example.qrcodehune.api.APIServer
import com.example.qrcodehune.base.ActivityBase.ActivityBase
import com.example.qrcodehune.model.Parameter
import com.example.qrcodehune.model.ResultDataScan
import com.example.qrcodehune.model.UsersScanCodeModel
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

abstract class FragmentBase: Fragment(), ZXingScannerView.ResultHandler {

    private var PERMISSION_REQUEST_CODE = 123
    private var dialog : Dialog? = null
    private lateinit var scannerView: ZXingScannerView
    private lateinit var code: String
    private lateinit var  btnCancel: Button
    private lateinit var btnYes: Button
    private lateinit var textDate: TextView
    lateinit var processBar: ProgressBar
    private val handler = Handler()

//    fun replaceFragment(fragment: Fragment){
//        baseActivity = activity as ActivityBase
//        baseActivity!!.replaceFragment(fragment)
//    }

    private fun openDialog(gravity: Int, constraintLayout: Int) {
        //try {
            dialog  = Dialog(context !!)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(constraintLayout)
            var window: Window? = dialog!!.window ?: return

            window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            var windowAttributes: WindowManager.LayoutParams = window!!.attributes
            windowAttributes.gravity = gravity
            window?.attributes = windowAttributes
            if (!dialog!!.isShowing){
                dialog!!.show()
            }
            else
                dialog!!.cancel()
//        }catch (e: InterruptedException){
//            e.printStackTrace()
//        }

    }

    private fun onClickButtonYes(scannerView: ZXingScannerView){
        btnYes = dialog!!.findViewById(R.id.btnYes)
        btnYes.setOnClickListener {
            dialog!!.cancel()
            scannerView.resumeCameraPreview(this@FragmentBase)
        }
    }
    private fun onClickButtonCancel(){
        btnCancel = dialog!!.findViewById(R.id.btnCancel)
        btnCancel.setOnClickListener { exitProcess(1) }
    }
    private fun textData(string: String) {
        textDate = dialog!!.findViewById(R.id.tvResult)
        textDate.text = string

    }
    fun initQRScan(frameLayout: FrameLayout){
        scannerView = ZXingScannerView(context !!)
        scannerView.setBackgroundColor(ContextCompat.getColor(context !!, R.color.colorAccent))
        scannerView.setBorderColor(ContextCompat.getColor(context !!, R.color.colorAccentDark))
        scannerView.setLaserColor(ContextCompat.getColor(context !!, R.color.colorPrimaryDark))
        scannerView.setAutoFocus(true)
        scannerView.setSquareViewFinder(true)
        scannerView.setResultHandler(this)
        frameLayout.addView(scannerView)
        startQRCamera()
    }
    private fun startQRCamera(){
        scannerView.startCamera()
    }
    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }
    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }
    override fun onStop() {
        super.onStop()
        scannerView.stopCamera()
    }
    override fun handleResult(result: Result?) {
        code = result?.text.toString()
       callAPI()
    }

    private fun callAPI(){
       // processBar= dialog!!.findViewById(R.id.pbQRCode) as ProgressBar
        processBar.visibility = View.VISIBLE
        var i:Int
        val parameterName = Parameter(code)
        val red = UsersScanCodeModel("UsersScanCode", "string", parameterName, 100)
        val api = APIClient().getRetro().create(APIServer::class.java)

        api.callUserScan(red).enqueue(object : Callback<ResultDataScan> {
            override fun onFailure(call: Call<ResultDataScan>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<ResultDataScan>,
                response: Response<ResultDataScan>
            ) {
                if (response.isSuccessful && response.body() != null) {

                    val postList = (response.body()?.Data)

                    //Toast.makeText(context, postList.toString(), Toast.LENGTH_SHORT).show()
                    if (postList == 0) {
                        openDialog(Gravity.CENTER, R.layout.custom_notification_qrcode_error)
                        onClickButtonCancel()
                        onClickButtonYes(scannerView)
                    } else {
                        openDialog(Gravity.CENTER, R.layout.custom_notification_qrcode_result)
                        textData(postList.toString())

                        scannerView.resumeCameraPreview(this@FragmentBase)
                    }
                    processBar.visibility = View.GONE
                }
            }

        })
    }
}








