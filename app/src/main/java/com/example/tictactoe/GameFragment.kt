package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tictactoe.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private var binding: FragmentGameBinding? = null
    private lateinit var adapter: Adapter
    private val list = mutableListOf<Square>()
    private var move = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        val args: GameFragmentArgs by navArgs()
        val displayMetrics = requireContext().resources.displayMetrics
        val width = displayMetrics.widthPixels.toFloat()
        //val width = 2 * displayMetrics.widthPixels / displayMetrics.density
        val size = args.gridSize
        for (i in 0 until size * size) {
            list.add(Square(R.drawable.square, width / size, false))
        }
        adapter = Adapter(list) {
            if (!list[it].isClicked) {
                if (move == 1) {
                    list[it].image = R.drawable.x

                    move = 0
                } else {
                    list[it].image = R.drawable.o
                    move = 1
                }
                list[it].isClicked = true
                adapter.notifyItemChanged(it)
                var first: Int
                var won = true
                for(i in 0 until size){
                    first = list[i*size].image
                    if(first == R.drawable.square)
                        continue
                    for(j in 0 until size){
                        if(list[i*size+j].image != first){
                            won = false
                            break
                        }
                    }
                    if(won){
                        for(k in list)
                            k.isClicked = true
                        if(first == R.drawable.o){
                            Toast.makeText(context, "O won", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(context, "X won", Toast.LENGTH_LONG).show()
                        }
                        return@Adapter
                    }
                    won = true
                }
                won = true
                for(i in 0 until size){
                    first = list[i].image
                    if(first == R.drawable.square)
                        continue
                    for(j in 0 until size){
                        if(list[i+j*size].image != first){
                            won = false
                            break
                        }
                    }
                    if(won){
                        for(k in list)
                            k.isClicked = true
                        if(first == R.drawable.o){
                            Toast.makeText(context, "O won", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(context, "X won", Toast.LENGTH_LONG).show()
                        }
                        return@Adapter
                    }
                    won = true
                }
                first = list[0].image
                if(first != R.drawable.square)
                    for(i in 0 until size){
                        if(list[i+i*size].image!=first){
                            won = false
                            break
                        }
                    }
                else  won = false
                if(won){
                    for(k in list)
                        k.isClicked = true
                    if(first == R.drawable.o){
                        Toast.makeText(context, "O won", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "X won", Toast.LENGTH_LONG).show()
                    }
                    return@Adapter
                }
                won = true
                first = list[size - 1].image
                if(first != R.drawable.square)
                    for(i in 0 until size){
                        if(list[(i+1)*size - i - 1].image!=first){
                            won = false
                            break
                        }
                    }
                else  won = false
                if(won){
                    for(k in list)
                        k.isClicked = true
                    if(first == R.drawable.o){
                        Toast.makeText(context, "O won", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "X won", Toast.LENGTH_LONG).show()
                    }
                    return@Adapter
                }
                var isTie = true
                for(i in list)
                    if(i.image == R.drawable.square) {
                        isTie = false
                        break
                    }
                if(isTie){
                    Toast.makeText(context, "Tie", Toast.LENGTH_LONG).show()
                }
            }
        }
        binding!!.recycler.adapter = adapter
        binding!!.recycler.layoutManager = GridLayoutManager(this.context, size)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
