package com.tsci.eitimpasaj.ui.store

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.tsci.eitimpasaj.databinding.FragmentTestsBinding
import com.tsci.eitimpasaj.model.AppViewModel

class TestsFragment : Fragment() {

    private var _binding: FragmentTestsBinding? = null

    private val sharedViewModel: AppViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val testsViewModel =
            ViewModelProvider(this).get(AppViewModel::class.java)

        _binding = FragmentTestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.testsWebView.loadUrl(sharedViewModel.getTargetUrl("tests").toString())

        binding.testsWebView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url)
                return false // then it is not handled by default action
            }
        })

        binding.testsWebView.canGoBack()
        binding.testsWebView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&  binding.testsWebView.canGoBack()) {
                binding.testsWebView.goBack()
                return@OnKeyListener true
            }
            false
        })



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}