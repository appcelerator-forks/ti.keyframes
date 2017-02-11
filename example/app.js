var TiAnimation = require('ti.animation');
var isAndroid = (Ti.Platform.osname == 'android');

var win = Ti.UI.createWindow({
    backgroundColor: '#fff',
    title: 'Ti.Animation Demo'
});

var lbl = Ti.UI.createLabel({
    bottom: 50,
    color: "#000"
});

var offset = 0;

var view = TiAnimation.createLottieView({
    file: 'sample_lottie.json',
    loop: false,
    bottom: 300,
    height: 120,
    width: 120,
    borderRadius: 60,
    autoStart: false
});

var view2 = TiAnimation.createKeyframeView({
    file: 'sample_keyframes.json',
    bottom: 100,
    width: 150,
    height: 150,
    loop: false,
    autoStart: false
});

var slider = Ti.UI.createSlider({
    value: 0,
    min: 0,
    max: 1,
    bottom: 10,
    width: 300
});

slider.addEventListener('change', seekToProgress);

win.add(view);
win.add(view2);
win.add(lbl);

win.add(createButtonWithAction('Start animation', startAnimation));
win.add(createButtonWithAction('Pause animation', pauseAnimation));
win.add(createButtonWithAction('Resume animation', resumeAnimation));

function onOpen(e) {
    if (!isAndroid) {
        return;
    }
    
    lbl.text = "Frame count: " + view2.getFrameCount() + " - Frame rate: " + view2.getFrameRate();
}

win.addEventListener("open", onOpen);
win.add(slider);

if (isAndroid) {
    win.open();
} else {
    var nav = Ti.UI.iOS.createNavigationWindow({
        window: win
    });
    nav.open();
}

function seekToProgress(e) {
    // view.seekToProgress(e.value);
    view2.seekToProgress(e.value);
}

function createButtonWithAction(title, action) {
    var btn = Ti.UI.createButton({
        title: title,
        top: offset
    });

    btn.addEventListener('click', action);

    offset += 40;
    return btn;
}

function startAnimation() {
    view.start();
    view2.start();
}

function pauseAnimation() {
    view.pause();
    view2.pause();
}

function resumeAnimation() {
    // view.resume();
    view2.resume();
}
