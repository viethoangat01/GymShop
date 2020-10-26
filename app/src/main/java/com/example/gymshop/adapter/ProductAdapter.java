package com.example.gymshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymshop.R;
import com.example.gymshop.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    Context context;
    int layout;
    ArrayList<Product> arrayList;

    public ProductAdapter(Context context, int layout, ArrayList<Product> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null);
        ItemHolder itemHolder=new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product=arrayList.get(position);
        holder.txtName.setText(product.getName());
        //ĐỊnh dạng kiểu giá sản phẩm
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(product.getPrice())+"đ");
        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_warning)
                .into(holder.imgProduct);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgProduct;
        public TextView txtName,txtPrice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct=(ImageView) itemView.findViewById(R.id.imageViewProduct);
            txtName=(TextView) itemView.findViewById(R.id.textViewName);
            txtPrice=(TextView) itemView.findViewById(R.id.textViewPrice);

        }
    }
}
