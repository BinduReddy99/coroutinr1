package com.binduinfo.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binduinfo.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // text_view.text = fakeApi()
        // CoroutineScope(IO).launch {
        //  fakeApi()
        // }
        fakeApi()

    }

    private fun fakeApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val executableTime = measureTimeMillis {
                val result = async {
                    getResultFromApi()
                }.await()

                val result2 = async {
                    getResultFromApi2(getResultFromApi())
                }.await()
                UI(result2)
                println("Debug result2 $result2")
            }
            println("Time Execution $executableTime")
        }
    }

    // parallel method 2
//    fun fakeApi(){
//        CoroutineScope(IO).launch {
//          val overallTime =  measureTimeMillis {
//              val result: Deferred<String> = async {
//                  getResultFromApi()
//              }
//              val result2: Deferred<String> = async {
//                  getResultFromApi2()
//              }
//              UI(result.await())
//              UI(result2.await())
//            }
//
//           // UI()
//            println("time===========:$overallTime")
//        }
//
//
//    }

    //Async
    // IO, Default, Main
//      fun fakeApi(){
//        progressBar.visibility = View.VISIBLE
//        val start = System.currentTimeMillis()
//        val parentJob = CoroutineScope(IO).launch {
//            //Child 1
//            // Child 2
//            val job2 = launch {
//                val t2 = measureTimeMillis {
//                    UI(getResultFromApi2())
//                }
//                println("Completion Job1 $t2")                //UI(getResultFromApi2())
//            }
//
//            val job1 = launch {
//                val t1 = measureTimeMillis {
//                    UI(getResultFromApi())
//                }
//
//                println("Completion Job1 $t1")
//            }
//
//
//
//        }
//
//        parentJob.invokeOnCompletion {
//            println("Completion time:${System.currentTimeMillis() - start}")
//        }
//
//        // basic
////        CoroutineScope(Dispatchers.IO).launch {
////            val job1 = withTimeoutOrNull(3000){
////                UI(getResultFromApi())
////            }
////            if(job1 == null){
////                UI("Cancelled Job")
////            }
////            //UI(getResultFromApi())
////        }
//
//    }

    private suspend fun getResultFromApi(): String {
        delay(4000)
        return "result 1"
    }

    private suspend fun getResultFromApi2(result2: String): String {
        delay(6000)
        return result2
    }

    private fun UI(resulit: String) {
        CoroutineScope(Main).launch {
            progressBar.visibility = View.GONE
            text_view.text = "${text_view.text.toString()}\n$resulit"
        }

    }
}
