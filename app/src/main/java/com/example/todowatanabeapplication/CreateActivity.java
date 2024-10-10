package com.example.todowatanabeapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.todowatanabeapplication.dto.ToDoItem;

import java.util.Calendar;

public class CreateActivity extends AppCompatActivity {
    private ToDoDatabase db;

    private EditText etToDoTitle;
    private EditText etDetailToDo;
    private CheckBox cbCompleted;
    private Button btnSelectDate;
    private TextView tvSelectedDate;
    private Spinner spinnerPriority;
    private Button btnSave;
    private String selectedDate = ""; //選択された日付を格納する変数

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create);

        // フィールドの関連付け
        etToDoTitle = findViewById(R.id.etToDoTitle);
        etDetailToDo = findViewById(R.id.etDetailToDo);
        cbCompleted = findViewById(R.id.cbCompleted);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        spinnerPriority = findViewById(R.id.spinnerPriority);
        btnSave = findViewById(R.id.btnSave);

        //データベースの初期化
        db = Room.databaseBuilder(getApplicationContext(),
                ToDoDatabase.class, "todo_database").allowMainThreadQueries().build();


        // Spinnerの設定（重要度の選択肢をセット）
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.priority_array,  // strings.xmlに定義した選択肢を使用
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(adapter);  // Spinnerにアダプターをセット

        // 日付を選択するボタンの処理
        btnSelectDate.setOnClickListener(v -> {
            // 現在の日付を取得
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // DatePickerDialogを表示
            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        // 月は0から始まるので+1する
                        monthOfYear = monthOfYear + 1;

                        // 選択された日付を文字列に変換
                        selectedDate = year1 + "-" + String.format("%02d", monthOfYear) + "-" + String.format("%02d", dayOfMonth);

                        // TextViewに選択された日付を表示
                        tvSelectedDate.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();

        });


        // タスクを保存するボタンの処理
        btnSave.setOnClickListener(v -> {
            // 入力された情報を取得
            String toDoTitle = etToDoTitle.getText().toString();
            String toDoDetaile = etDetailToDo.getText().toString();
            String deadline = selectedDate;
            String priority = spinnerPriority.getSelectedItem().toString(); // Spinnerで選ばれた重要度
            boolean isComplete = cbCompleted.isChecked();

            // Taskオブジェクトを作成
            ToDoItem newToDo = new ToDoItem(isComplete, toDoTitle, toDoDetaile, deadline, priority);

            db.toDoDao().insert(newToDo);

            finish();
        })
    ;}
}

