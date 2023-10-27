package dust.clientBase.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dust.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import dust.clientBase.model.SelectImageItem;
import dust.clientBase.model.ViewHolder;
import dust.clientBase.util.ImageLoaderOptions;

import java.util.ArrayList;


public class SelectedImageAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<SelectImageItem> mData;

	public SelectedImageAdapter(Context context, ArrayList<SelectImageItem> data) {
		mContext = context;
		mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public SelectImageItem getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_selected_item, null);

			View imageItem = ViewHolder.get(convertView, R.id.img_item);
			ViewGroup.LayoutParams layoutParams = imageItem.getLayoutParams();

			layoutParams.width = getImageWidth();
			layoutParams.height = getImageWidth();
			imageItem.setLayoutParams(layoutParams);
		}

		final ImageView imageItem = ViewHolder.get(convertView, R.id.img_item);
		SelectImageItem selectImageItem = mData.get(position);

		if (selectImageItem != null) {
			if (selectImageItem.getSid() == 100) {
				ImageLoader.getInstance().displayImage(
						"",
						imageItem,
						ImageLoaderOptions.getCommonNomemOption(R.drawable.add_image_selector, R.drawable.add_image_selector,
								R.drawable.add_image_selector));
			} else {
				String cameraPath = selectImageItem.getUrl();
				Bitmap bitmap = BitmapFactory.decodeFile(cameraPath);
				Bitmap mark = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.def_stc_icon);
				double width;
				double height;
				if (bitmap.getWidth() < 600) {
					width = 150;
					height = 150;
				} else {
					width = bitmap.getWidth() * 0.3;
					height = bitmap.getWidth() * 0.3;
				}
				imageItem.setImageBitmap(loadResBitmap(cameraPath,3));
				// imageItem.setImageBitmap(createBitmap(ImageUtil.zoomImage(bitmap,
				// width, height)));

			}
		}

		return convertView;
	}

	public static Bitmap loadResBitmap(String path, int scalSize) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = scalSize;
		Bitmap bmp = BitmapFactory.decodeFile(path, options);
		return bmp;
	}

	private int getImageWidth() {
		DisplayMetrics metric = mContext.getResources().getDisplayMetrics();
		final float scale = mContext.getResources().getDisplayMetrics().density;
		int dev = (int) (50 * scale + 0.5f);

		return (metric.widthPixels - dev) / (mContext.getResources().getBoolean(R.bool.isSw600) ? 9 : 4); //
	}

	/**
	 * ������Ҫˮӡ�ĵ�ͼƬ
	 * 
	 * @return
	 */
	private Bitmap createBitmap(Bitmap bitmap) {

		TextPaint textPaint = new TextPaint();
		textPaint.setTextSize(30.0f);// �����С
		textPaint.setTypeface(Typeface.DEFAULT_BOLD);// ����Ĭ�ϵĿ��
		textPaint.setColor(Color.RED);// ���õ���ɫ
		String test = "��ҵ��Pony������\nλ�ã�������������������·\nʱ�䣺2016-11-17";

		/**
		 * source : ��Ҫ���е��ַ��� paint : ���ʶ��� width : layout�Ŀ�ȣ��ַ����������ʱ�Զ����С� align
		 * : layout�Ķ��䷽ʽ����ALIGN_CENTER�� ALIGN_NORMAL�� ALIGN_OPPOSITE ���֡�
		 * spacingmult : ����м�࣬��������С��1.5f��ʾ�м��Ϊ1.5��������߶ȡ� spacingadd :
		 * �ڻ����о�����Ӷ���,ʵ�ʼ��Ϊspacingmult spacingadd �ĺ� includepad :
		 * ������ϣ��֪�������ѿ��Ը�֪һ��
		 */

		StaticLayout layout = new StaticLayout(test, textPaint, (int) (bitmap.getWidth() * 0.3), Alignment.ALIGN_NORMAL, 1.5F, 0.0F, true);
		int width = bitmap.getWidth(), hight = bitmap.getHeight();
		Bitmap icon = Bitmap.createBitmap(width, hight, Bitmap.Config.ARGB_8888); // ����һ���յ�BItMap
		Canvas canvas = new Canvas(icon);// ��ʼ���������Ƶ�ͼ��icon��

		Paint photoPaint = new Paint(); // ��������
		photoPaint.setDither(true); // ��ȡ��������ͼ�����
		photoPaint.setFilterBitmap(true);// ����һЩ

		Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());// ����һ��ָ�����¾��ε�����
		Rect dst = new Rect(0, 0, width, hight);// ����һ��ָ�����¾��ε�����
		canvas.drawBitmap(bitmap, src, dst, photoPaint);// ��photo ���Ż�������
		canvas.translate(20, 80);
		layout.draw(canvas);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return icon;
	}

}
