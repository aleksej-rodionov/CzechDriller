package space.rodionov.czechdriller.ui.driller;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.Locale;

import space.rodionov.czechdriller.R;
import space.rodionov.czechdriller.data.Word;

public class DrillerAdapterJava  extends ListAdapter<Word, DrillerAdapterJava.DrillerViewHolder> {

    private Boolean nativToForeign;
    Context context;
    private TextToSpeech mTTS;

    public void setNativToForeign(Boolean nativToForeign) {
        this.nativToForeign = nativToForeign;
    }

    /*protected*/public DrillerAdapterJava(@NonNull DiffUtil.ItemCallback diffCallback, Boolean nativToForeign, Context context) {
        super(diffCallback);
        this.nativToForeign = nativToForeign;
        this.context = context;
    }

    public class DrillerViewHolder extends CardStackView.ViewHolder {
        public TextView tvUpper;
        public TextView tvDowner;
        public CardView btnSpeak;

        public DrillerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUpper = itemView.findViewById(R.id.tv_upper);
            tvDowner = itemView.findViewById(R.id.tv_downer);
            btnSpeak = itemView.findViewById(R.id.btn_speak);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != CardStackView.NO_POSITION) {
                    tvDowner.setVisibility(View.VISIBLE);
                    if (btnSpeak.getVisibility() == View.INVISIBLE) {
                        btnSpeak.setVisibility(View.VISIBLE); // UNCOMMENT THIS AFTER IMPLEMENTING SOUNDS!!
                        btnSpeak.setEnabled(true); // UNCOMMENT THIS AFTER IMPLEMENTING SOUNDS!!
                    }
                }
            });

            btnSpeak.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != CardStackView.NO_POSITION) {
                    if (!nativToForeign) {
                        speak(tvUpper);
                    } else if (nativToForeign) {
                        speak(tvDowner);
                    }
                }
            });

            mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() { // or another context?
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = mTTS.setLanguage(Locale.ENGLISH);
                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("TTS", "Language not supported");
                        } else {
                            btnSpeak.setEnabled(true);
                        }
                    } else {
                        Log.e("TTS", "Initialization failed");
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public DrillerAdapterJava.DrillerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardstack_item, parent, false);
        DrillerAdapterJava.DrillerViewHolder drillerViewHolder = new DrillerAdapterJava.DrillerViewHolder(view);
        return drillerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrillerAdapterJava.DrillerViewHolder holder, int position) {
        Word currentWord = getItem(position);

        if (!nativToForeign) {
            holder.tvUpper.setText(currentWord.getForeign());
            holder.tvDowner.setText(currentWord.getRus());
        } else if (nativToForeign) {
            holder.tvUpper.setText(currentWord.getRus());
            holder.tvDowner.setText(currentWord.getForeign());
            holder.btnSpeak.setVisibility(View.INVISIBLE);
            holder.btnSpeak.setEnabled(false);
        }
        holder.tvDowner.setVisibility(View.INVISIBLE);
    }

    public Word getWordAt(int position) {
        return getItem(position);
    }

    public static class DrillerDiff extends DiffUtil.ItemCallback<Word> {
        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getRus().equals(newItem.getRus()) && oldItem.getForeign().equals(newItem.getForeign());
        }
    }

    private void speak(TextView tv) {
        String text = tv.getText().toString();
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
