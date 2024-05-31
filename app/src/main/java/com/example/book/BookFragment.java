package com.example.book;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import androidx.fragment.app.Fragment;

import java.util.UUID;

public class BookFragment extends Fragment {
    public EditText mTitleField;
    public Book mBook;
    public Button mDateButton;
    public CheckBox mReadedCheckBox;

    private static final String ARG_BOOK_ID = "book_id";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID bookId = (UUID) getArguments().getSerializable(ARG_BOOK_ID);
        mBook = BookLab.get(getActivity()).getBook(bookId);
    }
    public static BookFragment newInstance(UUID bookId)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK_ID, bookId);
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book, container,
                false);
        mTitleField = (EditText) v.findViewById(R.id.book_title);
        mTitleField.setText(mBook.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBook.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = (Button)v.findViewById(R.id.book_date);
        mDateButton.setText(mBook.getDate().toString());
        mDateButton.setEnabled(false);
        mReadedCheckBox = (CheckBox)v.findViewById(R.id.book_readed);
        mReadedCheckBox.setChecked(mBook.isReaded());
        mReadedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBook.setReaded(isChecked);
            }
        });
        return v;
    }
}
