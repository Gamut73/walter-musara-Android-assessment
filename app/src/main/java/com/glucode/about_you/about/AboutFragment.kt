package com.glucode.about_you.about

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import java.io.File

class AboutFragment: Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var engineer: Engineer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val engineerName = arguments?.getString("name")
        engineer = MockData.engineers.first { it.name == engineerName }

        if (engineer.defaultImageName.isNotEmpty()) {
            val imageUri = Uri.fromFile(File(requireContext().filesDir, engineer.defaultImageName))
            displayProfilePic(imageUri)
        }

        setUpQuestions()
    }

    private fun setUpQuestions() {
        binding.engineerProfile.name.text = engineer.name
        binding.engineerProfile.role.text = engineer.role

        binding.engineerProfile.profileImage.setOnClickListener {
            pickProfilePicLauncher.launch("image/*")
        }

        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }

    private val pickProfilePicLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { imageUri ->
                displayProfilePic(imageUri)

                val filename = "${engineer.name}_profile_pic.jpg"
                MockData.engineers.first { it.name == engineer.name }.defaultImageName = filename
                saveProfilePicInDefaultLocation(imageUri, filename)
            }
    }

    private fun saveProfilePicInDefaultLocation(uri: Uri, filename: String) {
        requireContext().contentResolver.openInputStream(uri)?.use { inputStream ->
           File(requireContext().filesDir, filename).outputStream().use { outputStream ->
               inputStream.copyTo(outputStream)
           }
        }
    }

    private fun displayProfilePic(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.engineerProfile.profileImage)
    }
}