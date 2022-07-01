package com.irfan.sadaparcel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.irfan.sadaparcel.cart.ShoppingCartFragment
import com.irfan.sadaparcel.inventory.InventoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    navigateTo(InventoryFragment.newInstance())
                    true
                }
                R.id.cart -> {
                    navigateTo(ShoppingCartFragment.newInstance())
                    true
                }
                else->{
                    false
                }
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,InventoryFragment.newInstance())
            .commitNow()
    }

    private fun navigateTo(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commitNow()
    }
}