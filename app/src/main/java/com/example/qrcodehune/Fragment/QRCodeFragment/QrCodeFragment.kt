package com.example.qrcodehune.Fragment.QRCodeFragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import com.example.qrcodehune.R

import com.example.qrcodehune.base.FragmentBase.FragmentBase

import com.example.qrcodehune.databinding.FragmentQrcodeBinding


class QrCodeFragment : FragmentBase() {
    private var binding: FragmentQrcodeBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qrcode, container, false)
        initQRScan(binding!!.flQRCode)
        binding?.pbQRCode?.visibility = View.GONE
        processBar = binding?.pbQRCode !!
        return binding?.root
    }

}
