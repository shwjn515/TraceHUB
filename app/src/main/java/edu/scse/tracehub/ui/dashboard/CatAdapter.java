package edu.scse.tracehub.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {
    private Context context;
    private List<Cat> cats = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView type;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            image = (ImageView) itemView.findViewById(R.id.cat_image);
            type = (TextView) itemView.findViewById(R.id.cat_type);
        }
        public CardView getCardView() {
            return cardView;
        }
    }
    //放入项目的数据集
    public CatAdapter(Context context, List<Cat> cats) {
        this.context = context;
        this.cats = cats;
    }
    //create new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {//设置上下文环境
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.cat_item, parent, false);
        return new ViewHolder(view);
    }
    //布置item内容
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cat cat = cats.get(position);
        holder.type.setText(cat.getType());
        holder.image.setImageResource(cat.getImgId());
        if(null != onItemClickListener)
        {
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(v.getContext(),"holder.type.getText()",Toast.LENGTH_LONG);
                    toast.show();
                    onItemClickListener.onItemClick(v,position);
                }
            });
        }
    }
    //回调接口
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount() {
        return cats.size();
    }


}
