package com.shellcore.android.capital.ui.accounts.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.shellcore.android.capital.R
import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.utils.inflate
import com.shellcore.android.capital.utils.toCurrency
import kotlinx.android.synthetic.main.item_account.view.*

/**
 * Created by MOGC. 2018/02/20.
 */
class AccountsAdapter(var accounts: List<Account>, private var listener: AccountListener): RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_account), listener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(accounts[position])
    }

    override fun getItemCount() = accounts.size

    fun updateAccounts(accounts: List<Account>) {
        this.accounts = accounts
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val listener: AccountListener): RecyclerView.ViewHolder(itemView) {
        fun bind(account: Account) {
            with(itemView) {
                txtAccount.text = account.name
                val accountTypes = resources.getStringArray(R.array.accounts_types)
                txtAccountType.text = accountTypes[account.type]
                txtBalance.text = account.balance.toCurrency()
                cnsItem.setOnClickListener {
                    listener.onClick(account)
                }
            }
        }

    }
}

