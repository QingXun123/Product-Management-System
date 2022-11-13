package io.renren.modules.app.entity;

import lombok.Data;

import java.util.List;

@Data
public class InformationListEntity {

    public InformationListEntity() {
    }

    public InformationListEntity(InformationDownloadEntity informationDownload, List<InformationDownloadEntity> informationDownloads) {
        this.informationDownload = informationDownload;
        this.informationDownloads = informationDownloads;
    }

    private InformationDownloadEntity informationDownload;

    private List<InformationDownloadEntity> informationDownloads;
}
