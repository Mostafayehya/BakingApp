package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.DataModels.Step;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.StepDetailsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mostafayehya on 08/10/17.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<Step> mStepList;
    boolean isTwoPane = false;

    public static final String CLICKED_POSITION_KEY = "clickedItemPosition";
    public static final String STEP_LIST_KEY = "stepsList";

    public StepAdapter(Context context, ArrayList<Step> stepList, boolean isTwoPane) {
        mContext = context;
        mStepList = stepList;
        mInflater = LayoutInflater.from(context);
        this.isTwoPane = isTwoPane;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.step_item, null);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {

        String shortDescription = mStepList.get(position).shortDescription;

        holder.shortDiscription.setText(shortDescription);

    }

    @Override
    public int getItemCount() {

        if (mStepList.size() == 0)
            return 1;
        else
            return mStepList.size();
    }

    public void setStepsList(ArrayList<Step> newStepList) {
        mStepList.clear();
        mStepList.addAll(newStepList);
        notifyDataSetChanged();

    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.step_short_description)
        TextView shortDiscription;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int adapterPosition = getAdapterPosition();

            if (!isTwoPane) {
                Intent toStartStepDetailActivity = new Intent(mContext, StepDetailsActivity.class);
                toStartStepDetailActivity.putExtra(CLICKED_POSITION_KEY, adapterPosition);
                toStartStepDetailActivity.putExtra(STEP_LIST_KEY, mStepList);
                mContext.startActivity(toStartStepDetailActivity);
            } else {
                //send the data to the activity through the onClick callback of the onClickListner interface in the Recipe
                //details activity


            }
        }
    }
}
