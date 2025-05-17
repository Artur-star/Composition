package ru.knyazev.composition.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.knyazev.composition.R
import ru.knyazev.composition.databinding.FragmentGameFinishedBinding
import ru.knyazev.composition.domain.entities.GameResult

class GameFinishedFragment : Fragment() {
    private lateinit var gameResult: GameResult
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("Fragment Game Finished binding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            retryGame()
        }
        binding.buttonRetry.setOnClickListener { retryGame() }
        observeViewModel()
    }

    private fun observeViewModel() {
        with(binding) {
            emojiResult.setImageResource(
                if (gameResult.winner) R.drawable.ic_smile
                else R.drawable.ic_sad
            )

            tvRequiredAnswers.text = resources.getString(R.string.required_score)
                .format(gameResult.gameSettings.minCountOfRightAnswers)

            tvScoreAnswers.text = resources.getString(R.string.score_answers)
                .format(gameResult.countOfRightAnswer)

            tvRequiredPercentage.text = resources.getString(R.string.required_percentage)
                .format(gameResult.gameSettings.minPercentOfRightAnswers)

            tvScorePercentage.text = resources.getString(R.string.score_percentage)
                .format(percentage())
        }
    }

    private fun percentage(): Int =
        with(gameResult) {
            if (countOfRightAnswer == 0) 0
            else (countOfRightAnswer / countOfQuestions.toDouble() * 100).toInt()
        }


    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_GAME_RESULT = "key_game"

        fun newInstance(result: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, result)
                }
            }
        }
    }
}