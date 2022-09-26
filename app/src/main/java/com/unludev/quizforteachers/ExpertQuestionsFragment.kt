package com.unludev.quizforteachers



import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.unludev.quizforteachers.databinding.FragmentExpertQuestionsBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

//subject fragmenttaki konu butonundan(tv) sorulari gosterecegin fragmenta deger gonder
// fragment açıldığında o değeri kontrol edip ona göre soruları çağırıp kullanıcıya gösterebilirsin

class ExpertQuestionsFragment : Fragment() {

    val s1json = "expertquestions.json"
    val s2json = "headmasterquestions.json"
    lateinit var binding: FragmentExpertQuestionsBinding
    var correct = 0
    var wrong = 0
    var currentQuestions = 0
    lateinit var questionsItems: ArrayList<QuestionsItems>
    var  mLastClickTime = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpertQuestionsBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val que = ExpertQuestionsFragmentArgs.fromBundle(it).que
            when(que) {
                "s1" -> getAllQuestions(s1json)
                "s2" -> getAllQuestions(s2json)
//                "s3" -> getAllQuestions(s2json)
//                "s4" -> getAllQuestions(s2json)
//                "s5" -> getAllQuestions(s2json)
//                "s6" -> getAllQuestions(s2json)
//                "s7" -> getAllQuestions(s2json)
            }
        }

        setQuestionScreen(currentQuestions)
        clickA()
        clickB()
        clickC()
        clickD()
        clickE()
    }

    fun getAllQuestions(json: String) {
        questionsItems = ArrayList()
        val jsonquiz: String = loadJsonFromAsset(json)
        try {
            val jsonObject = JSONObject(jsonquiz)
            val questions = jsonObject.getJSONArray(json.removeSuffix(".json"))
            for (i in 0..questions.length()) {
                var question: JSONObject = questions.getJSONObject(i)

                var questionsString = question.getString("question")
                var questionHeadString = question.getString("questionHead")
                var answerAString = question.getString("answerA")
                var answerBString = question.getString("answerB")
                var answerCString = question.getString("answerC")
                var answerDString = question.getString("answerD")
                var answerEString = question.getString("answerE")
                var correctString = question.getString("correctAnswer")

                questionsItems.add(
                    QuestionsItems(
                        questionsString,
                        questionHeadString,
                        answerAString,
                        answerBString,
                        answerCString,
                        answerDString,
                        answerEString,
                        correctString,
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJsonFromAsset(s: String): String {
        var json = ""
        try {
            val inputStream: InputStream? = context?.assets?.open(s)
            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }
            inputStream?.read(buffer)
            inputStream?.close()
            json = buffer?.let { String(it) }!!
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }


    fun setQuestionScreen(currentQuestions: Int) {
        binding.tvExpertQuestion.text = questionsItems[currentQuestions].question
        binding.tvExpertQuestionHead.text = questionsItems[currentQuestions].questionHead
        binding.tvAnswerA.text = questionsItems[currentQuestions].answerA
        binding.tvAnswerB.text = questionsItems[currentQuestions].answerB
        binding.tvAnswerC.text = questionsItems[currentQuestions].answerC
        binding.tvAnswerD.text = questionsItems[currentQuestions].answerD
        binding.tvAnswerE.text = questionsItems[currentQuestions].answerE
    }

    fun clickA() {
        binding.tvAnswerA.setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
            binding.tvAnswerA.setEnabled(false)

            //do actual work
            if (questionsItems[currentQuestions].answerA.equals(questionsItems[currentQuestions].correctAnswer)) {
                correct++
                binding.tvAnswerA.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerA.setTextColor(resources.getColor(R.color.white))
            } else {
                wrong++
                binding.tvAnswerA.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerA.setTextColor(resources.getColor(R.color.white))

            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = object : Runnable {
                    override fun run() {
                        currentQuestions++
                        setQuestionScreen(currentQuestions)
                        binding.tvAnswerA.setBackgroundColor(resources.getColor(R.color.white))
                        binding.tvAnswerA.setTextColor(resources.getColor(R.color.text_secondery_color))
                    }
                }
                handler.postDelayed(runnable, 1000)
            } else {
                val action= ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
            binding.tvAnswerA.setEnabled(true)
        })
    }
    fun clickB() {
        binding.tvAnswerB.setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
            binding.tvAnswerB.setEnabled(false)

            //do actual work
            if (questionsItems[currentQuestions].answerB.equals(questionsItems[currentQuestions].correctAnswer)) {
                correct++
                binding.tvAnswerB.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerB.setTextColor(resources.getColor(R.color.white))
            } else {
                wrong++
                binding.tvAnswerB.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerB.setTextColor(resources.getColor(R.color.white))

            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = object : Runnable {
                    override fun run() {
                        currentQuestions++
                        setQuestionScreen(currentQuestions)
                        binding.tvAnswerB.setBackgroundColor(resources.getColor(R.color.white))
                        binding.tvAnswerB.setTextColor(resources.getColor(R.color.text_secondery_color))
                    }
                }
                handler.postDelayed(runnable, 1000)
            } else {
                val action = ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
            binding.tvAnswerB.setEnabled(true)
        })
    }
    fun clickC() {
        binding.tvAnswerC.setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
            binding.tvAnswerC.setEnabled(false)

            //do actual work
            if (questionsItems[currentQuestions].answerC.equals(questionsItems[currentQuestions].correctAnswer)) {
                correct++
                binding.tvAnswerC.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerC.setTextColor(resources.getColor(R.color.white))
            } else {
                wrong++
                binding.tvAnswerC.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerC.setTextColor(resources.getColor(R.color.white))

            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = object : Runnable {
                    override fun run() {
                        currentQuestions++
                        setQuestionScreen(currentQuestions)
                        binding.tvAnswerC.setBackgroundColor(resources.getColor(R.color.white))
                        binding.tvAnswerC.setTextColor(resources.getColor(R.color.text_secondery_color))
                    }
                }
                handler.postDelayed(runnable, 1000)
            } else {
                val action = ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
            binding.tvAnswerC.setEnabled(true)
        })
    }
    fun clickD() {

        binding.tvAnswerD.setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
            binding.tvAnswerD.setEnabled(false)

            //do actual work
            if (questionsItems[currentQuestions].answerD.equals(questionsItems[currentQuestions].correctAnswer)) {
                correct++
                binding.tvAnswerD.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerD.setTextColor(resources.getColor(R.color.white))
            } else {
                wrong++
                binding.tvAnswerD.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerD.setTextColor(resources.getColor(R.color.white))

            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = object : Runnable {
                    override fun run() {
                        currentQuestions++
                        setQuestionScreen(currentQuestions)
                        binding.tvAnswerD.setBackgroundColor(resources.getColor(R.color.white))
                        binding.tvAnswerD.setTextColor(resources.getColor(R.color.text_secondery_color))
                    }
                }
                handler.postDelayed(runnable, 1000)
            } else {
                val action = ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
            binding.tvAnswerD.setEnabled(true)
        })
    }
    fun clickE() {
        binding.tvAnswerE.setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
            binding.tvAnswerE.setEnabled(false)

            //do actual work
            if (questionsItems[currentQuestions].answerE.equals(questionsItems[currentQuestions].correctAnswer)) {
                correct++
                correct++
                binding.tvAnswerE.setBackgroundColor(resources.getColor(R.color.green))
                binding.tvAnswerE.setTextColor(resources.getColor(R.color.white))
            } else {
                wrong++
                binding.tvAnswerE.setBackgroundColor(resources.getColor(R.color.red))
                binding.tvAnswerE.setTextColor(resources.getColor(R.color.white))

            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = Runnable {
                    currentQuestions++
                    setQuestionScreen(currentQuestions)
                    binding.tvAnswerE.setBackgroundColor(resources.getColor(R.color.white))
                    binding.tvAnswerE.setTextColor(resources.getColor(R.color.text_secondery_color))
                }
                handler.postDelayed(runnable, 1000)
            } else {
                val action = ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
            binding.tvAnswerE.setEnabled(true)
        })
    }
}