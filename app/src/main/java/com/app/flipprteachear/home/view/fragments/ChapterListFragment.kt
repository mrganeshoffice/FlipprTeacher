package com.app.flipprteachear.home.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.flipprteachear.R
import com.app.flipprteachear.databinding.FragmentChapterListBinding

class ChapterListFragment : Fragment() {
    lateinit var viewBinding:FragmentChapterListBinding

   /* private val chapterAdapterr by lazy {
        chapterAdapter(this, requireActivity(), pref)
    }
*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_chapter_list, container, false)
        with(viewBinding){
            //rvUchapters.adapter =
        }

        return  viewBinding.root
    }



}