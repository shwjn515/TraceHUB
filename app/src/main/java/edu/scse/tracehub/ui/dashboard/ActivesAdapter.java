package edu.scse.tracehub.ui.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
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

import edu.scse.tracehub.R;

public class
ActivesAdapter extends RecyclerView.Adapter<ActivesAdapter.ViewHolder> {
    private Context context;
    private List<Active> actives = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private List<Bitmap> imgs = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            image = (ImageView) itemView.findViewById(R.id.cat_image);
            text = (TextView) itemView.findViewById(R.id.cat_type);
        }
        public CardView getCardView() {
            return cardView;
        }
    }
    //放入项目的数据集
    public ActivesAdapter(Context context, List<Active> actives, List<Bitmap> imgs) {
        this.context = context;
        this.actives = actives;
        this.imgs = imgs;
    }
    //create new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {//设置上下文环境
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }
    //布置item内容
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Active active = actives.get(position);
        Bitmap img = imgs.get(position);
        holder.text.setText(active.getText());
        holder.image.setImageBitmap(img);
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
        return actives.size();
    }


}
