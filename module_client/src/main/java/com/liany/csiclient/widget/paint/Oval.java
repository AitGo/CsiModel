package com.liany.csiclient.widget.paint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Oval extends ShapeAbstract {

	RectF mRectF;
	public Oval(Shapable paintTool) {
		super(paintTool);
		mRectF = new RectF();
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		mRectF.set(x1, y1, x2, y2);
		canvas.drawOval(mRectF, paint);
	}

	@Override
	public String toString() {
		return " oval";
	}
}
