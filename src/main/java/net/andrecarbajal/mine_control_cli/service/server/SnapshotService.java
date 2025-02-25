package net.andrecarbajal.mine_control_cli.service.server;

import net.andrecarbajal.mine_control_cli.config.AppConfiguration;
import net.andrecarbajal.mine_control_cli.service.download.FileDownloadService;
import net.andrecarbajal.mine_control_cli.util.FileUtil;
import org.springframework.stereotype.Service;

@Service
public class SnapshotService extends MojangService {
    public SnapshotService(AppConfiguration appConfiguration, FileUtil fileUtil, FileDownloadService fileDownloadService) {
        super(appConfiguration, fileUtil, fileDownloadService);
    }

    @Override
    public String type() {
        return "snapshot";
    }
}
