package com.spaceappschallenge.weatheronmars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class NoConnectionAlertDialog extends DialogFragment {

	public interface OnAlertDialogClickListener {
		public void onPositiveClick();

		public void onNegativeClick();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.network_connectivity_error_message);

		builder.setPositiveButton(R.string.go_to_settings,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						((OnAlertDialogClickListener) getActivity())
								.onPositiveClick();
					}
				});

		builder.setNegativeButton(R.string.proceed_with_cache,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						((OnAlertDialogClickListener) getActivity())
								.onNegativeClick();
					}
				});
		builder.setCancelable(false);

		return builder.create();
	}
}
