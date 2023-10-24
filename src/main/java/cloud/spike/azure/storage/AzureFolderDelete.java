package cloud.spike.azure.storage;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.ListBlobsOptions;

public class AzureFolderDelete {

    private static final String storageAccountName = System.getenv("STORAGE_ACCOUNT");

    private static final String accountKey = System.getenv("AZURE_ACCOUNT_KEY");
    private static final String connectionString = String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net", storageAccountName, accountKey);
    private static final String containerName = "test";
    private static final String directoryName = "models/albert_xlarge_token_classifier_conll03_pipeline_en_4.4.2_3.4_1685066592482/"; //Ensure it ends with a '/'

    public static void main(String[] args) {
        // Create a BlobContainerClient
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient();

        // List all blobs under the specified directory including subdirectories
        containerClient.listBlobs(new ListBlobsOptions().setPrefix(directoryName), null).forEach(
                blobItem -> {
                    // Delete each blob
                    System.out.println("Deleting blob: " + blobItem.getName());
                    containerClient.getBlobClient(blobItem.getName()).delete();
                }
        );
    }

}
