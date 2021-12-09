package com.example.dopecalculator.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel:ViewModel() {


    private val _elementos=MutableLiveData<MutableList<String>>()
    val elementos:LiveData<MutableList<String>>
        get()=_elementos

    private var ans=0.0

    init {
        _elementos.value= mutableListOf()
    }


    fun salvaElementos(screenValue:String):Boolean {
        var numero=""
        var dotCount=0
        _elementos.value?.clear()

        for(digito in screenValue){
            Log.d("msg","dig $digito")
            if ((digito.code in 48..57) || digito=='.'){
                Log.d("msg","dig cod ${digito.code}")
                if (digito=='.') dotCount++

                if (dotCount==2){
                    return false
                }

                numero+=digito

            } else{
                _elementos.value?.add(numero)
                _elementos.value?.add(digito.toString())
                numero=""
                dotCount=0

            }
        }
        _elementos.value!!.add(numero)
        Log.d("msg","save bums $elementos")
        return true
    }



    fun realizaOperacaoMatematica() {
        var doLoop=true

        while(doLoop){
            for (i in 0 until _elementos.value!!.size){
                if(i>=_elementos.value!!.size)break
                if(_elementos.value!![i]=="*"){
                    ans=_elementos.value!![i-1]!!.toDouble()*_elementos.value!![i+1].toDouble()
                    _elementos.value!!.removeAt(i)
                    _elementos.value!!.removeAt(i)
                    _elementos.value!!.add(i,ans.toString())
                    _elementos.value!!.removeAt(i-1)
                    doLoop=true
                    break
                }else if(_elementos.value!![i]=="/"){
                    ans=_elementos.value!![i-1].toDouble()/_elementos.value!![i+1].toDouble()
                    _elementos.value!!.removeAt(i)
                    _elementos.value!!.removeAt(i)
                    _elementos.value!!.add(i,ans.toString())
                    _elementos.value!!.removeAt(i-1)
                    doLoop=true
                    break
                }else{
                    doLoop=false
                }
            }
        }

        doLoop=true

        while(doLoop){
            for (i in 0 until _elementos.value!!.size){
                if(i>=_elementos.value!!.size)break
                when(_elementos.value!![i]){
                    "+"->{
                        ans=_elementos.value!![i-1].toDouble()+_elementos.value!![i+1].toDouble()
                        _elementos.value!!.removeAt(i)
                        _elementos.value!!.removeAt(i)
                        _elementos.value!!.add(i,ans.toString())
                        _elementos.value!!.removeAt(i-1)
                        doLoop=true
                        break
                    }
                    "-"->{
                        ans=_elementos.value!![i-1].toDouble()-_elementos.value!![i+1].toDouble()
                        _elementos.value!!.removeAt(i)
                        _elementos.value!!.removeAt(i)
                        _elementos.value!!.add(i,ans.toString())
                        _elementos.value!!.removeAt(i-1)
                        doLoop=true
                        break
                    }
                    else->{
                        doLoop=false
                    }
                }

            }
            ans=_elementos.value!![0].toDouble()
            Log.d("msg","elem value ${_elementos.value}")
        }


    }


    fun getAns():Double{
        return ans
    }

}