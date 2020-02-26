function Timer(offsetSeconds) {
	//proxy current object pointer using closure
	this.offsetSeconds = offsetSeconds;
    this.start = function(dom) {
    	var start = parseInt(new Date().getTime() / 1000);
        setInterval(() => {
            var end = parseInt(new Date().getTime() / 1000)
            var duration = parseInt(end - start + this.offsetSeconds)
            var hour = Math.floor(duration / 3600);
            var minutes = Math.floor(duration % 3600 / 60);
            var seconds = Math.floor(duration % 60);
            var durationSeconds = hour + ':' + minutes + ':' + seconds;
            dom.innerHTML = durationSeconds;
        }, 10);
    }

}