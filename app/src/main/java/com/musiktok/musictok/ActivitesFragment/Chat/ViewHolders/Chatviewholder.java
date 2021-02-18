package com.musiktok.musictok.ActivitesFragment.Chat.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.musiktok.musictok.ActivitesFragment.Chat.Chat_GetSet;
import com.musiktok.musictok.ActivitesFragment.Chat.ChatAdapter;
import com.musiktok.musictok.ActivitesFragment.Chat.Chat_GetSet;
import com.musiktok.videoapp.R;

public class Chatviewholder extends RecyclerView.ViewHolder {
    public TextView message,datetxt,txtTime;
    public  View view;
    public Chatviewholder(View itemView) {
        super(itemView);
        view = itemView;
        this.message = view.findViewById(R.id.msgtxt);
        this.datetxt=view.findViewById(R.id.datetxt);
        txtTime=view.findViewById(R.id.txtTime);
    }

    public void bind(final Chat_GetSet item, final ChatAdapter.OnLongClickListener long_listener) {
        message.setOnLongClickListener(v -> {
                long_listener.onLongclick(item,v);
                return false;

        });
    }
}
