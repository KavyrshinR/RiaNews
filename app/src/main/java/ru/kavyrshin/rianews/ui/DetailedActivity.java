package ru.kavyrshin.rianews.ui;


import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.kavyrshin.rianews.R;
import ru.kavyrshin.rianews.domain.global.models.News;
import ru.kavyrshin.rianews.presentation.presenters.DetailedNewsPresenter;
import ru.kavyrshin.rianews.presentation.views.DetailedNewsView;

public class DetailedActivity extends BaseActivity implements DetailedNewsView {

    public static final String NEWS_ID_EXTRA = "News id extra";

    private SimpleDateFormat dateParser = new SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault());

    private FrameLayout imageLayout;
    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvSubTitle;
    private TextView tvDateTime;
    private TextView tvText;

    @InjectPresenter
    DetailedNewsPresenter presenter;

    @ProvidePresenter
    DetailedNewsPresenter provideDetailedNewsPresenter() {
        return application().getApplicationComponent().detailedNewsComponent()
                .newsId(getIntent().getIntExtra(NEWS_ID_EXTRA, -1))
                .build()
                .presenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        imageLayout = (FrameLayout) findViewById(R.id.imageLayout);
        imageView = (ImageView) findViewById(R.id.imageView);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSubTitle = (TextView) findViewById(R.id.tvSubTitle);
        tvDateTime = (TextView) findViewById(R.id.tvDateTime);
        tvText = (TextView) findViewById(R.id.tvText);

        tvText.setMovementMethod(LinkMovementMethod.getInstance());

    }


    @Override
    public void showNews(News news) {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        imageLayout.setLayoutParams(new LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels/3));

        Picasso.get()
                .load(news.getBigImgUrl())
                .into(imageView);

        tvTitle.setText(news.getName());
        tvSubTitle.setText(news.getCategory().getName());

        Date date = new Date(news.getUnixtime());

        tvDateTime.setText(news.getUnixtime() != 0 ? dateParser.format(date) : "");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvText.setText(Html.fromHtml(news.getText() != null ? news.getText() : "", Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvText.setText(Html.fromHtml(news.getText() != null ? news.getText() : ""));
        }

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}