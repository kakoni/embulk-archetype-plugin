package ${package};

import java.util.List;

import org.embulk.config.ConfigDiff;
import org.embulk.config.ConfigSource;
import org.embulk.config.TaskReport;
import org.embulk.config.TaskSource;

import org.embulk.spi.InputPlugin;
import org.embulk.spi.PageOutput;
import org.embulk.spi.Schema;

import org.embulk.util.config.Config;
import org.embulk.util.config.ConfigDefault;
import org.embulk.util.config.ConfigMapper;
import org.embulk.util.config.ConfigMapperFactory;
import org.embulk.util.config.Task;
import org.embulk.util.config.TaskMapper;
import org.embulk.util.config.units.SchemaConfig;


public class ${type.substring(0,1).toUpperCase()}${type.substring(1)}InputPlugin implements InputPlugin {
  
  private static final ConfigMapperFactory CONFIG_MAPPER_FACTORY = ConfigMapperFactory.builder().addDefaultModules().build();
  private static final ConfigMapper CONFIG_MAPPER = CONFIG_MAPPER_FACTORY.createConfigMapper();
  private static final TaskMapper TASK_MAPPER = CONFIG_MAPPER_FACTORY.createTaskMapper();;

  public interface PluginTask extends Task {
    @Config("columns")
    SchemaConfig getSchemaConfig();
  }

  @Override
  public ConfigDiff transaction(ConfigSource config, InputPlugin.Control control) {
      final PluginTask task = CONFIG_MAPPER.map(config, PluginTask.class);
      final Schema schema = task.getSchemaConfig().toSchema();
      final int taskCount = 1;

      return resume(task.dump(), schema, taskCount, control);
  }

  @Override
  public TaskReport run(TaskSource taskSource, Schema schema, int taskIndex, PageOutput output) {
    PluginTask task = TASK_MAPPER.map(taskSource, PluginTask.class);
    throw new UnsupportedOperationException("InputPlugin.run method is not implemented yet");
  }

  @Override
  public void cleanup(TaskSource taskSource, Schema schema, int taskCount, List<TaskReport> successTaskReports) {
    // do nothing
  }

  @Override
  public ConfigDiff resume(TaskSource taskSource, Schema schema, int taskCount, InputPlugin.Control control) {
    control.run(taskSource, schema, taskCount);
    return CONFIG_MAPPER_FACTORY.newConfigDiff();
  }



  @Override
  public ConfigDiff guess(ConfigSource config) {
    return CONFIG_MAPPER_FACTORY.newConfigDiff();
  }    
}
