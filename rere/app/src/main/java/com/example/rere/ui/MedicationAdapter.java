package com.example.rere.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rere.R;
import com.example.rere.data.Medication;

public class MedicationAdapter extends ListAdapter<Medication, MedicationAdapter.MedicationViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(Medication medication);
    }

    private final OnDeleteClickListener deleteClickListener;

    public MedicationAdapter(OnDeleteClickListener deleteClickListener) {
        super(DIFF_CALLBACK);
        this.deleteClickListener = deleteClickListener;
    }

    private static final DiffUtil.ItemCallback<Medication> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Medication>() {
                @Override
                public boolean areItemsTheSame(@NonNull Medication oldItem, @NonNull Medication newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Medication oldItem, @NonNull Medication newItem) {
                    return oldItem.name.equals(newItem.name)
                            && equalsNullSafe(oldItem.dosage, newItem.dosage)
                            && equalsNullSafe(oldItem.frequency, newItem.frequency)
                            && oldItem.active == newItem.active;
                }

                private boolean equalsNullSafe(String a, String b) {
                    if (a == null && b == null) return true;
                    if (a == null || b == null) return false;
                    return a.equals(b);
                }
            };

    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medication, parent, false);
        return new MedicationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        Medication medication = getItem(position);
        holder.bind(medication);
    }

    class MedicationViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvDetails;
        private final ImageButton btnDelete;

        MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvMedicationName);
            tvDetails = itemView.findViewById(R.id.tvMedicationDetails);
            btnDelete = itemView.findViewById(R.id.btnDeleteMedication);
        }

        void bind(Medication medication) {
            tvName.setText(medication.name);
            String details = "";
            if (medication.dosage != null) {
                details += medication.dosage;
            }
            if (medication.frequency != null && !medication.frequency.isEmpty()) {
                if (!details.isEmpty()) details += " • ";
                details += medication.frequency;
            }
            tvDetails.setText(details);

            btnDelete.setOnClickListener(v -> {
                if (deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(medication);
                }
            });
        }
    }
}
