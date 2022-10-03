package com.unludev.quizforteachers



import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.unludev.quizforteachers.databinding.FragmentExpertQuestionsBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream



class ExpertQuestionsFragment : Fragment() {


    private val s8json = "dijyet.json"
    private val s3json = "guvokul.json"
    private val s4json = "guvokul2.json"
    private val s5json = "sosyaletki.json"
    private val s6json = "sosyaletki2.json"
    private val s7json = "sosyaletkilesimveiletsm78.json"
    private val s1json = "egtkapsam.json"
    private val s2json = "egtkapsam2.json"
    private val s9json = "egtmarge.json"
    private val s10json = "egtmarge2.json"
    private val s11json = "ceviklim.json"
    private val s12json = "ozelegtm.json"
    private val s13json = "ozelegtm2.json"
    private val s14json = "ogrsur.json"
    private val s15json = "ogrsur2.json"
    private val s16json = "olcmevedeg.json"
    private val s17json = "olcmevedeg2.json"

    private lateinit var binding: FragmentExpertQuestionsBinding
    private var correct = 0
    private var wrong = 0
    private var currentQuestions = 0
    private lateinit var questionsItems: ArrayList<QuestionsItems>
    private var  mLastClickTime = 0L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpertQuestionsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//TODO("subject stringlerine enum yaz")
        arguments?.let {
            when(ExpertQuestionsFragmentArgs.fromBundle(it).que) {
                "Eğitimde Kapsayıcılık - 1" -> getAllQuestions(s1json)
                "Eğitimde Kapsayıcılık - 2" -> getAllQuestions(s2json)
                "Eğitim Araştırmaları ve Ar-Ge Çalışmaları - 1" -> getAllQuestions(s9json)
                "Eğitim Araştırmaları ve Ar-Ge Çalışmaları - 2" -> getAllQuestions(s10json)
                "Güvenli Okul ve Okul Güvenliği - 1" -> getAllQuestions(s3json)
                "Güvenli Okul ve Okul Güvenliği - 2" -> getAllQuestions(s4json)
                "Sosyal Etkileşim ve İletişim - 1" -> getAllQuestions(s5json)
                "Sosyal Etkileşim ve İletişim - 2" -> getAllQuestions(s6json)
                "Sosyal Etkileşim ve İletişim - 3" -> getAllQuestions(s7json)
                "Dijital Yetkinlik" -> getAllQuestions(s8json)
                "Çevre Eğitimi ve İklim Değişikliği" -> getAllQuestions(s11json)
                "Özel Eğitim ve Rehberlik - 1" -> getAllQuestions(s12json)
                "Özel Eğitim ve Rehberlik - 2" -> getAllQuestions(s13json)
                "Öğrenme ve Öğretme Süreçleri - 1" -> getAllQuestions(s14json)
                "Öğrenme ve Öğretme Süreçleri - 2" -> getAllQuestions(s15json)
                "Ölçme ve Değerlendirme - 1" -> getAllQuestions(s16json)
                "Ölçme ve Değerlendirme - 2" -> getAllQuestions(s17json)
            }
        }

        setQuestionScreen(currentQuestions)
        clickA()
        clickB()
        clickC()
        clickD()
        clickE()
    }

    private fun getAllQuestions(json: String) {
        questionsItems = ArrayList()
        val jsonquiz: String = loadJsonFromAsset(json)
        try {
            val jsonObject = JSONObject(jsonquiz)
            val questions = jsonObject.getJSONArray(json.removeSuffix(".json"))
            for (i in 0..questions.length()) {
                val question: JSONObject = questions.getJSONObject(i)
                val questionsString = question.getString("question")
                val questionHeadString = question.getString("questionHead")
                val answerAString = question.getString("answerA")
                val answerBString = question.getString("answerB")
                val answerCString = question.getString("answerC")
                val answerDString = question.getString("answerD")
                val answerEString = question.getString("answerE")
                val correctString = question.getString("correctAnswer")

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
        with(binding){
            tvExpertQuestion.text = questionsItems[currentQuestions].question
            tvExpertQuestionHead.text = questionsItems[currentQuestions].questionHead
            tvAnswerA.text = questionsItems[currentQuestions].answerA
            tvAnswerB.text = questionsItems[currentQuestions].answerB
            tvAnswerC.text = questionsItems[currentQuestions].answerC
            tvAnswerD.text = questionsItems[currentQuestions].answerD
            tvAnswerE.text = questionsItems[currentQuestions].answerE
        }

    }

    private fun clickA() {

        binding.tvAnswerA.apply { this.setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 5000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
            this.isEnabled = false

            //do actual work
            if (!questionsItems[currentQuestions].answerA.equals(questionsItems[currentQuestions].correctAnswer)) {
                wrong++
                this.setBackgroundColor(resources.getColor(R.color.red))
                this.setTextColor(resources.getColor(R.color.white))
                Toast.makeText(context, "Dogru Cevap: ${questionsItems[currentQuestions].correctAnswer}", Toast.LENGTH_SHORT).show()
            } else {
                correct++
                this.setBackgroundColor(resources.getColor(R.color.green))
                this.setTextColor(resources.getColor(R.color.white))
            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = Runnable {
                    currentQuestions++
                    setQuestionScreen(currentQuestions)
                    this@apply.setBackgroundColor(resources.getColor(R.color.white))
                    this@apply.setTextColor(resources.getColor(R.color.text_secondery_color))
                }
                handler.postDelayed(runnable, 5000)
            } else {
                val action= ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
            this.isEnabled = true
        }) }
    }
    private fun clickB() {
        binding.tvAnswerB.apply {
            this.setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 5000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
                this.isEnabled = false

            //do actual work
            if (questionsItems[currentQuestions].answerB.equals(questionsItems[currentQuestions].correctAnswer)) {
                correct++
                this.setBackgroundColor(resources.getColor(R.color.green))
                this.setTextColor(resources.getColor(R.color.white))
            } else {
                wrong++
                this.setBackgroundColor(resources.getColor(R.color.red))
                this.setTextColor(resources.getColor(R.color.white))
                Toast.makeText(context, "Dogru Cevap: ${questionsItems[currentQuestions].correctAnswer}", Toast.LENGTH_SHORT).show()

            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = Runnable {
                    currentQuestions++
                    setQuestionScreen(currentQuestions)
                    this@apply.setBackgroundColor(resources.getColor(R.color.white))
                    this@apply.setTextColor(resources.getColor(R.color.text_secondery_color))
                }
                handler.postDelayed(runnable, 5000)
            } else {
                val action = ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
                this.isEnabled = true
        }) }
    }
    private fun clickC() {
        binding.tvAnswerC.apply {
        this.setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 5000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
            this.isEnabled = false

            //do actual work
            if (questionsItems[currentQuestions].answerC.equals(questionsItems[currentQuestions].correctAnswer)) {
                correct++
                this.setBackgroundColor(resources.getColor(R.color.green))
                this.setTextColor(resources.getColor(R.color.white))
            } else {
                wrong++
                this.setBackgroundColor(resources.getColor(R.color.red))
                this.setTextColor(resources.getColor(R.color.white))
                Toast.makeText(context, "Dogru Cevap: ${questionsItems[currentQuestions].correctAnswer}", Toast.LENGTH_SHORT).show()

            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = Runnable {
                    currentQuestions++
                    setQuestionScreen(currentQuestions)
                    this@apply.setBackgroundColor(resources.getColor(R.color.white))
                    this@apply.setTextColor(resources.getColor(R.color.text_secondery_color))
                }
                handler.postDelayed(runnable, 5000)
            } else {
                val action = ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
            this.isEnabled = true
        })
        }

    }
    private fun clickD() {

        binding.tvAnswerD.apply{
            this.setOnClickListener(View.OnClickListener {
                // mis-clicking prevention, using threshold of 1 second
                if (SystemClock.elapsedRealtime() - mLastClickTime < 5000) {
                    return@OnClickListener
                }

                //store time of button click
                mLastClickTime = SystemClock.elapsedRealtime()
                this.isEnabled = false

                //do actual work
                if (questionsItems[currentQuestions].answerD.equals(questionsItems[currentQuestions].correctAnswer)) {
                    correct++
                    this.setBackgroundColor(resources.getColor(R.color.green))
                    this.setTextColor(resources.getColor(R.color.white))
                } else {
                    wrong++
                    this.setBackgroundColor(resources.getColor(R.color.red))
                   this.setTextColor(resources.getColor(R.color.white))
                    Toast.makeText(context, "Dogru Cevap: ${questionsItems[currentQuestions].correctAnswer}", Toast.LENGTH_SHORT).show()

                }

                if (currentQuestions < questionsItems.size - 1) {
                    val handler = Handler(Looper.myLooper()!!)
                    val runnable = Runnable {
                        currentQuestions++
                        setQuestionScreen(currentQuestions)
                        this@apply.setBackgroundColor(resources.getColor(R.color.white))
                        this@apply.setTextColor(resources.getColor(R.color.text_secondery_color))
                    }
                    handler.postDelayed(runnable, 5000)
                } else {
                    val action = ExpertQuestionsFragmentDirections
                        .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                    it.findNavController().navigate(action)
                }
                this.isEnabled = true
            })
        }
    }
    private fun clickE() {

        binding.tvAnswerE.apply { setOnClickListener(View.OnClickListener {
            // mis-clicking prevention, using threshold of 1 second
            if (SystemClock.elapsedRealtime() - mLastClickTime < 5000) {
                return@OnClickListener
            }

            //store time of button click
            mLastClickTime = SystemClock.elapsedRealtime()
            this.isEnabled = false

            //do actual work
            if (questionsItems[currentQuestions].answerE.equals(questionsItems[currentQuestions].correctAnswer)) {
                correct++
                correct++
                this.setBackgroundColor(resources.getColor(R.color.green))
                this.setTextColor(resources.getColor(R.color.white))
            } else {
                wrong++
                this.setBackgroundColor(resources.getColor(R.color.red))
                this.setTextColor(resources.getColor(R.color.white))
                Toast.makeText(context, "Dogru Cevap: ${questionsItems[currentQuestions].correctAnswer}", Toast.LENGTH_SHORT).show()

            }

            if (currentQuestions < questionsItems.size - 1) {
                val handler = Handler(Looper.myLooper()!!)
                val runnable = Runnable {
                    currentQuestions++
                    setQuestionScreen(currentQuestions)
                    this@apply.setBackgroundColor(resources.getColor(R.color.white))
                    this@apply.setTextColor(resources.getColor(R.color.text_secondery_color))
                }
                handler.postDelayed(runnable, 5000)
            } else {
                val action = ExpertQuestionsFragmentDirections
                    .actionExpertQuestionFragmentToResultFragment(correct.toString(), wrong.toString())
                it.findNavController().navigate(action)
            }
            this.isEnabled = true
        }) }
    }
}