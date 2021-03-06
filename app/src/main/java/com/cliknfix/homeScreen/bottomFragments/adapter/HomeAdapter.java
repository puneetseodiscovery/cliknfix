package com.cliknfix.homeScreen.bottomFragments.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cliknfix.R;
import com.cliknfix.homeScreen.HomeScreenActivity;
import com.cliknfix.homeScreen.bottomFragments.model.BeanHomeFragment;
import com.cliknfix.submitProblem.SubmitProblemFragment;
import com.cliknfix.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    ArrayList<BeanHomeFragment> list = new ArrayList<>();

    public HomeAdapter(Context context, ArrayList<BeanHomeFragment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ivCat.setImageResource(list.get(position).getCatImg());
        holder.tvCatName.setText(list.get(position).getCatName());
        holder.llCatItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "" + list.get(position).getCatName(), Toast.LENGTH_SHORT).show();
            loadFragment(list.get(position).getCatName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_cat_view_item)
        LinearLayout llCatItem;
        @BindView(R.id.iv_category)
        ImageView ivCat;
        @BindView(R.id.iv_next)
        ImageView ivNext;
        @BindView(R.id.tv_category)
        TextView tvCatName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            init();
        }

        public void init() {
            tvCatName.setTypeface(Utility.typeFaceForSemiBoldText(context));
        }
    }

    public void loadFragment(String category) {
        FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
        SubmitProblemFragment fragment = new SubmitProblemFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
