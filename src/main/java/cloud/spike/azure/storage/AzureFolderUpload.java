package cloud.spike.azure.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AzureFolderUpload {

    private static final String storageAccountName = System.getenv("STORAGE_ACCOUNT");

    private static final String accountKey = System.getenv("AZURE_ACCOUNT_KEY");
    private static final String connectionString = String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net", storageAccountName, accountKey);
    private static final String containerName = "test";
    private static final String folderPath = "/media/danilo/Data/Danilo/JSL/tmp/S3/public/tmpgraphs";

    public static void main(String[] args) {
        // Create a BlobContainerClient
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient();

        // Upload the folder and its contents recursively
        uploadFolder(containerClient, folderPath, "ner/graphs");
    }

    private static void uploadFolder(BlobContainerClient containerClient, String folderPath, String targetFolder) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Upload subfolder recursively
                    uploadFolder(containerClient, file.getAbsolutePath(), targetFolder + "/" + file.getName());
                } else {
                    // Upload file to Azure Blob Storage
                    String relativePath = Paths.get(folderPath).relativize(file.toPath()).toString();
                    String blobName = targetFolder + "/" + relativePath.replace(File.separator, "/");
                    BlobClient blobClient = containerClient.getBlobClient(blobName);
                    blobClient.uploadFromFile(file.getAbsolutePath());
                    System.out.println("Uploaded: " + blobName);
                }
            }
        }
    }
}
