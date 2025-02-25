package net.andrecarbajal.mine_control_cli.service.server;

import net.andrecarbajal.mine_control_cli.config.MineControlConfig;
import net.andrecarbajal.mine_control_cli.model.ServerLoader;
import net.andrecarbajal.mine_control_cli.service.download.FileDownloadService;
import net.andrecarbajal.mine_control_cli.util.FileUtil;
import org.jline.terminal.Terminal;
import org.springframework.core.io.ResourceLoader;
import org.springframework.shell.style.TemplateExecutor;

import java.nio.file.Path;

public abstract class AbstractUnmoddedService extends AbstractLoaderService {
    public AbstractUnmoddedService(MineControlConfig mineControlConfig, FileUtil fileUtil, FileDownloadService fileDownloadService) {
        super(mineControlConfig, fileUtil, fileDownloadService);
    }

    public void createServer(ServerLoader loader, String serverName, String version, Terminal terminal, ResourceLoader resourceLoader, TemplateExecutor templateExecutor) {
        Path serverPath = prepareServerDirectory(serverName);
        try {
            if (version == null || !getVersions().contains(version))
                version = selectVersion(terminal, resourceLoader, templateExecutor);
            downloadServerFiles(version, serverPath);
            acceptEula(serverPath);
            saveServerInfo(serverPath, loader, version);
            logServerCreationSuccess(serverName, version);
        } catch (Exception e) {
            deleteServerDirectory(mineControlConfig.getApplicationPath().getApplicationPath().resolve(serverName));
        }
    }

    protected abstract String getDownloadUrl(String version);

    private void downloadServerFiles(String version, Path serverPath) {
        String downloadUrl = getDownloadUrl(version);
        fileDownloadService.downloadFile(downloadUrl, serverPath, "server.jar");
    }

    private void saveServerInfo(Path serverPath, ServerLoader serverLoader, String version) {
        fileUtil.saveServerInfo(serverPath, serverLoader, version);
    }
}
