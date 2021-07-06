package com.adikrena.submissioncapstone.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adikrena.submissioncapstone.core.data.Resource
import com.adikrena.submissioncapstone.core.ui.RecipeAdapter
import com.adikrena.submissioncapstone.databinding.FragmentHomeBinding
import com.adikrena.submissioncapstone.detail.DetailRecipeActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val recipeAdapter = RecipeAdapter()
            recipeAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailRecipeActivity::class.java)
                intent.putExtra(DetailRecipeActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.recipes.observe(viewLifecycleOwner, { recipes ->
                if (recipes != null) {
                    when (recipes) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            recipeAdapter.setData(recipes.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvRecipe) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = recipeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}