package com.imeet.yunchanbao.constrant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.imeet.yunchanbao.tools.LogUtils;

public class ConstClass {
	/**
	 * ����String��ShareedPreferences��
	 */
	public static void setSharedPreferencesVal(Context context,
			String sharedPerferencesName, String sharedPerferencesKey,
			String sharedPerferencesVal) {
		SharedPreferences sharedPrefernces = context.getSharedPreferences(
				sharedPerferencesName, Activity.MODE_PRIVATE);
		sharedPrefernces.edit()
				.putString(sharedPerferencesKey, sharedPerferencesVal).commit();
	}

	/**
	 * ����int��ShareedPreferences��
	 */
	public static void setSharedPreferencesVal(Context context,
			String sharedPerferencesName, String sharedPerferencesKey,
			int sharedPerferencesVal) {
		SharedPreferences sharedPrefernces = context.getSharedPreferences(
				sharedPerferencesName, Activity.MODE_PRIVATE);
		sharedPrefernces.edit()
				.putInt(sharedPerferencesKey, sharedPerferencesVal).commit();
	}

	/**
	 * ����boolean��ShareedPreferences��
	 */
	public static void setSharedPreferencesVal(Context context,
			String sharedPerferencesName, String sharedPerferencesKey,
			boolean sharedPerferencesVal) {
		SharedPreferences sharedPrefernces = context.getSharedPreferences(
				sharedPerferencesName, Activity.MODE_PRIVATE);
		sharedPrefernces.edit()
				.putBoolean(sharedPerferencesKey, sharedPerferencesVal)
				.commit();
	}

	/**
	 * ����float��ShareedPreferences��
	 */
	public static void setSharedPreferencesVal(Context context,
			String sharedPerferencesName, String sharedPerferencesKey,
			float sharedPerferencesVal) {
		SharedPreferences sharedPrefernces = context.getSharedPreferences(
				sharedPerferencesName, Activity.MODE_PRIVATE);
		sharedPrefernces.edit()
				.putFloat(sharedPerferencesKey, sharedPerferencesVal).commit();
	}

	/**
	 * ȡ��SharedPreferences�б��������(String) Ĭ��ֵΪ��
	 */
	public static String getSharedPreferencesVal(Context context,
			String sharedPerferencesName, String sharedPerferencesKey) {
		String sharedPreferencesVal = context.getSharedPreferences(
				sharedPerferencesName, 0).getString(sharedPerferencesKey, "");
		return sharedPreferencesVal;
	}

	/**
	 * ȡ��ShareedPreferences�б��������(int) Ĭ��ֵΪ0
	 */
	public static int getSharedPreferencesVal(String sharedPerferencesName,
			Context context, String sharedPerferencesKey) {
		if (context != null) {
			int sharedPreferencesVal = context.getSharedPreferences(
					sharedPerferencesName, 0).getInt(sharedPerferencesKey, 0);
			return sharedPreferencesVal;
		}
		return 0;
	}

	/**
	 * ȡ��ShareedPreferences�б��������(boolean) Ĭ��ֵΪfalse
	 */
	public static boolean getSharedPreferencesVal(String sharedPerferencesName,
			String sharedPerferencesKey, Context context) {
		if (context != null) {
			boolean sharedPreferencesVal = context.getSharedPreferences(
					sharedPerferencesName, 0).getBoolean(sharedPerferencesKey,
					false);
			return sharedPreferencesVal;
		}
		return false;
	}

	/**
	 * ȡ��ShareedPreferences�б��������(float) Ĭ��ֵΪ0
	 */
	public static float getFloatSharedPreferencesVal(Context context,
			String sharedPerferencesName, String sharedPerferencesKey) {
		if (context != null) {
			float sharedPreferencesVal = context.getSharedPreferences(
					sharedPerferencesName, 0).getFloat(sharedPerferencesKey, 0);
			return sharedPreferencesVal;
		}
		return 0;
	}

	/**
	 * ����Object����
	 */
	public static void setObjectSharedPreferencesVal(Activity activity,
			Object model, String sharedPerferencesName,
			String sharedPerferencesKey) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			// ���������OutputStream��
			oos = new ObjectOutputStream(baos);
			oos.writeObject(model);
			SharedPreferences listModelSharedPreferences = activity
					.getSharedPreferences(sharedPerferencesName,
							Activity.MODE_PRIVATE);
			// ���������byte�����У�����Base64Ϊ����
			String listModelBase64 = new String(Base64.encodeBase64(baos
					.toByteArray()));
			SharedPreferences.Editor editor = listModelSharedPreferences.edit();
			// ���������ַ���д������Ϊ:sharedPerferencesKey�ļ���
			editor.putString(sharedPerferencesKey, listModelBase64);
			editor.commit();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ȡ��Object���͵�
	 */
	public static Object getobjectSharedPreferencesVal(Activity activity,
			String sharedPerferencesName, String sharedPerferencesKey) {
		Object model = null;
		if (activity != null) {
			SharedPreferences listModelSharedPreferences = activity
					.getSharedPreferences(sharedPerferencesName,
							Activity.MODE_PRIVATE);
			String listModelBase64 = listModelSharedPreferences.getString(
					sharedPerferencesKey, "");
			if (listModelBase64 != null && listModelBase64 != "") {
				// ��Base64λ���н���
				byte[] base64Bytes = Base64.decodeBase64(listModelBase64
						.getBytes());
				ByteArrayInputStream bais = new ByteArrayInputStream(
						base64Bytes);
				try {
					ObjectInputStream ois = new ObjectInputStream(bais);
					model = ois.readObject();
				} catch (StreamCorruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return model;
	}

	/**
	 * ���SharedPreferences
	 */
	@SuppressWarnings("static-access")
	public static void clearSharedPreferences(Context context,
			String sharedPreferencesName) {
		SharedPreferences spf = context.getSharedPreferences(
				sharedPreferencesName, context.MODE_PRIVATE);
		spf.edit().clear().commit();
	}

	/**
	 * ���ܣ���⵱ǰURL�Ƿ�����ӻ��Ƿ���Ч, ����������������� 3 ��, ��� 3 �ζ����ɹ�����Ϊ�õ�ַ������
	 * 
	 * @param urlStr
	 *            ָ��URL�����ַ
	 * @return URL
	 */
	public static synchronized int isConnect(String urlStr) {
		int counts = 0;
		HttpURLConnection con;
		int state = -1;
		if (urlStr == null || urlStr.length() <= 0) {
			return state;
		}
		while (counts < Const.REQUEST_COUNT) {
			long start = 0;
			try {
				URL url = new URL(urlStr);
				start = System.currentTimeMillis();
				con = (HttpURLConnection) url.openConnection();
				state = con.getResponseCode();
				LogUtils.i("����Ͽ���URLһ����Ҫ:"
						+ (System.currentTimeMillis() - start) + "����");
				if (state == 200) {
					// state = "ok";
					LogUtils.i("���ӵ�ַ:{" + urlStr + ",״̬:����}");
				} else if (state == 404) {
					// retu = "404";
					LogUtils.i("���ӵ�ַ:{" + urlStr + ",״̬:��ַ404}");
				} else {
					LogUtils.i("���ӵ�ַ:{" + urlStr + ",״̬:�����쳣,������룺" + state
							+ "}");
				}
				break;
			} catch (Exception ex) {
				counts++;
				LogUtils.i("����Ͽ���URLһ����Ҫ:"
						+ (System.currentTimeMillis() - start) + "����");
				LogUtils.i("���ӵ� " + counts + " �Σ�" + "���ӵ�ַ:{" + urlStr
						+ "--������,������룺" + state + "}");
				continue;
			}
		}
		return state;
	}

	/**
	 * ͨ���ṩ��url��ַ����ͼƬ
	 * @param url
	 * @return
	 */
	public static Bitmap getURLimage(String url) {
		Bitmap bmp = null;
		try {
			URL myurl = new URL(url);
			// �������
			HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
			conn.setConnectTimeout(6000);// ���ó�ʱ
			conn.setDoInput(true);
			conn.setUseCaches(false);// ������
			conn.connect();
			InputStream is = conn.getInputStream();// ���ͼƬ��������
			bmp = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bmp;
	}
	/**
	 * ��dpת��Ϊ����
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int Dp2Px(Context context, float dp) { 
	    final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int) (dp * scale + 0.5f); 
	}
	
}
