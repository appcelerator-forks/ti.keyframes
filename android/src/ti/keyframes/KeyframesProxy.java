/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.keyframes;

import android.app.Activity;
import com.facebook.keyframes.*;
import com.facebook.keyframes.deserializers.*;
import com.facebook.keyframes.model.*;
import java.io.InputStream;
import java.io.IOException;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFileFactory;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.view.TiCompositeLayout;
import android.view.LayoutInflater;
import android.content.res.Resources;
import android.view.View;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;


@Kroll.proxy(creatableInModule=TiKeyframesModule.class)
public class KeyframesProxy extends TiViewProxy
{
	// Standard Debugging variables
	private static final String LCAT = "Keyframes";
	private static final boolean DBG = TiConfig.LOGD;
	private InputStream stream;
	private KFImage kfImage;
	private ImageView imageView;
	private KeyframesDrawable kfDrawable;

	private class Keyframes extends TiUIView
	{
		public Keyframes(TiViewProxy proxy) {
			super(proxy);
			
			
			String packageName = proxy.getActivity().getPackageName();
            Resources resources = proxy.getActivity().getResources();
            View videoWrapper;
            int resId_videoHolder = -1;
            int resId_video       = -1;

            resId_videoHolder = resources.getIdentifier("layout", "layout", packageName);
            resId_video       = resources.getIdentifier("ImageView", "id", packageName);
            
            LayoutInflater inflater     = LayoutInflater.from(getActivity());
            videoWrapper = inflater.inflate(resId_videoHolder, null);
            if (resId_video != 0){
                imageView   = (ImageView)videoWrapper.findViewById(resId_video);            
				imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
				imageView.setImageDrawable(kfDrawable);
				imageView.setImageAlpha(0);
                setNativeView(videoWrapper);
            } else {
                Log.e(LCAT, "Layout not found");
            }
			
		}

		@Override
		public void processProperties(KrollDict d)
		{
			super.processProperties(d);
		}
	}

	@Kroll.method
    public void initialize() {
        
    }
	
	@Kroll.method
    public void stopAnimation() {
        kfDrawable.stopAnimation();
    }

	@Kroll.method
    public void stopAnimationAtLoopEnd() {
        kfDrawable.stopAnimationAtLoopEnd();
    }

    @Kroll.method
    public void startAnimation() {
        kfDrawable.startAnimation();
    }
	
    
    @Kroll.method
    public void playOnce() {
        kfDrawable.playOnce();
    }
	
    
	@Kroll.method
    public void pauseAnimation() {
		kfDrawable.pauseAnimation();
    }
	
	@Kroll.method
    public void resumeAnimation() {
		kfDrawable.resumeAnimation();
    }
	
	@Kroll.method
    public void seekToProgress(float pos) {
		kfDrawable.seekToProgress(pos);
    }

	@Kroll.method
    public int getFrameCount() {
		return kfImage.getFrameCount();
    }
	
	@Kroll.method
    public int getFrameRate() {
		return kfImage.getFrameRate();
    }

	// Constructor
	public KeyframesProxy()
	{
		super();
	}

	@Override
	public TiUIView createView(Activity activity)
	{
		Log.i(LCAT, "createView");
		TiUIView view = new Keyframes(this);
		view.getLayoutParams().autoFillsHeight = true;
		view.getLayoutParams().autoFillsWidth = true;
		return view;
	}
	
	private String getPathToApplicationAsset(String assetName)
	{
		// The url for an application asset can be created by resolving the specified
		// path with the proxy context. This locates a resource relative to the 
		// application resources folder
		
		String result = resolveUrl(null, assetName);
		return result;
	}

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options)
	{
		super.handleCreationDict(options);

		if (options.containsKey("file")) {
			Log.i(LCAT, "file");
			try {
				String url = getPathToApplicationAsset(options.getString("file"));
	            TiBaseFile file = TiFileFactory.createTitaniumFile(new String[] { url }, false);      
				stream = file.getInputStream();
				Log.i(LCAT, stream+ "");
				kfImage = KFImageDeserializer.deserialize(stream);
				kfDrawable = new KeyframesDrawableBuilder().withImage(kfImage).build();
			} catch (IOException e){
				Log.i(LCAT, "error " + e);
			}
		}
	}
}
