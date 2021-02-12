package com.example.myfishypal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class viewproductFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_product, container, false)
    }






    lateinit var submit: Button
    lateinit var ref: DatabaseReference
    lateinit var productlist: MutableList<products>
    lateinit var listView: ListView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getting user input
        productlist = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("products")
        submit = view.findViewById<Button>(R.id.submit_veiw_product)
        listView = view.findViewById(R.id.productview)

        submit.setOnClickListener {
            findNavController().navigate(R.id.action_viewproductFragment_to_FirstFragment)
        }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {
                    for (t in snapshot.children) {
                        val product = t.getValue(products::class.java)
                        productlist.add(product!!)
                    }
                    val cont: Context
                    cont = requireActivity().getApplicationContext();
                    val adapter = viewproductadapter(cont, R.layout.fragment_product, productlist)
                    listView.adapter = adapter

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        });
    }


}