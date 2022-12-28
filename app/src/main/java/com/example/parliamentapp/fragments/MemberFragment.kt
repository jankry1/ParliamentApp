package com.example.parliamentapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.parliamentapp.MainActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.parliamentapp.R
import com.example.parliamentapp.databinding.FragmentMemberBinding
import com.example.parliamentapp.viewModels.MemberViewModel
import com.example.parliamentapp.viewModels.PhotoViewModel

/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: displaying all data about the PM
 */

class Member : Fragment() {
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var memberViewModel: MemberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMemberBinding>(
            inflater, R.layout.fragment_member, container, false)

        val name = MemberArgs.fromBundle(requireArguments()).memberName
        binding.nameText.text = name

        memberViewModel = ViewModelProvider(this)[MemberViewModel::class.java]
        photoViewModel = ViewModelProvider(this)[PhotoViewModel::class.java]

        memberViewModel.members.observe(viewLifecycleOwner, Observer {
            binding.infoText.text = memberViewModel.extractInfo(it, name)
            val personNumber = memberViewModel.extractPersonNumber(it, name)

            //Save-button
            binding.saveButton.setOnClickListener{
                memberViewModel.addComment(binding.userInput.text.toString(), personNumber)
            }
            //Like button
            binding.likeButton.setOnClickListener{
                memberViewModel.addLike(1, personNumber)
            }
            //Dislike-button
            binding.dislikeButton.setOnClickListener {
                memberViewModel.addDislike(1, personNumber)
            }
        })

        //Member's photo. Glide library does photo caching
        photoViewModel.pictureUrl.observe(viewLifecycleOwner, Observer {
            val pictureUrl = photoViewModel.extractPictureUrl(it, name)
            Glide.with(this).load("https://avoindata.eduskunta.fi/${pictureUrl}").into(binding.memberPhoto)
        })

        (activity as AppCompatActivity).supportActionBar?.title = "Parliament member"

        return binding.root
    }
}
