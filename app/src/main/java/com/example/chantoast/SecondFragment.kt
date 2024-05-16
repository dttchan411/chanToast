package com.example.chantoast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val maxCount = arguments?.getInt("count", 0) ?: 0
        val randomCount = Random.nextInt(maxCount + 1)
        val explain: TextView = view.findViewById(R.id.explain)
        val realRandomCount: TextView = view.findViewById(R.id.random_count)

        explain.text = "Here is a random number between 0 and $maxCount"

        realRandomCount.text = "$randomCount"

        realRandomCount.setOnClickListener {
            parentFragmentManager.setFragmentResult("randomResult", Bundle().apply {
                putInt("randomCount", randomCount)
            })
            parentFragmentManager.popBackStack()
        }
        return view
    }

    companion object {
        fun newInstance(count: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt("count", count)
            fragment.arguments = args
            return fragment
        }
    }
}
