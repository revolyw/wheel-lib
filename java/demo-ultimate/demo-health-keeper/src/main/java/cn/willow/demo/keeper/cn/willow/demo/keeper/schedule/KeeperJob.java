package cn.willow.demo.keeper.cn.willow.demo.keeper.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * TODO comments
 *
 * @author Willow
 * @date 1/3/18
 */
public class KeeperJob extends QuartzJobBean {
	private String name;

	/**
	 * Invoked if a Job data map entry with that name
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println(this.name);
	}
}
