package server_client_stoppuhr;

/**
 *
 * @author Nagl
 */
public class Response {
    private Boolean master;
    private long count;
    private Boolean running;
    private long time;

    public Response(Boolean master, long count, Boolean running, long time) {
	this.master = master;
	this.count = count;
	this.running = running;
	this.time = time;
    }

    Response() {
    }

    
    public boolean isMaster() {
        return master != null && master;
    }

    public long getCount() {
        return count;
    }

    public boolean isRunning() {
        return running != null && running;
    }

    public long getTime() {
        return time;
    }

    public void setMaster(Boolean master) {
	this.master = master;
    }

    public void setCount(long count) {
	this.count = count;
    }

    public void setRunning(Boolean running) {
	this.running = running;
    }

    public void setTime(long time) {
	this.time = time;
    }

    @Override
    public String toString() {
	return "Response{" + "master=" + master + ", count=" + count + ", running=" + running + ", time=" + time + '}';
    }
    
}