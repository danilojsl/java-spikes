package cloud.spike.azure.storage;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.UserDelegationKey;
import com.azure.storage.blob.sas.BlobContainerSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;

import java.io.IOException;
import java.time.OffsetDateTime;

public class AzureApp {

    public static void main( String[] args ) throws IOException {
//        listFilesWithDefaultAzureCredentials();
//            listFilesWithUserDelegationSASTokens();
//        uploadFileToAzure();
        downloadFileFromAzure();
    }

    private static void listFilesWithUserDelegationSASTokens() {
        // Setup the storage account and blob clients
        String storageAccountName = System.getenv("STORAGE_ACCOUNT");
        String containerName = "test";

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint("https://" + storageAccountName + ".blob.core.windows.net")
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        // Get a user delegation key for the current date and for the next 7 days
        OffsetDateTime keyStartsOn = OffsetDateTime.now();
        UserDelegationKey userDelegationKey = blobServiceClient.getUserDelegationKey(keyStartsOn,
                keyStartsOn.plusDays(7));

        // Define the SAS parameters
        BlobServiceSasSignatureValues sasSignatureValues = new BlobServiceSasSignatureValues(
                OffsetDateTime.now().plusDays(1), // Let it expire in 1 day
                BlobContainerSasPermission.parse("rlw") // read, list, write permissions
        );

        // Generate the SAS token
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        String sasToken = containerClient.generateUserDelegationSas(sasSignatureValues, userDelegationKey);

        // You can now use the SAS token to create a new BlobServiceClient or BlobContainerClient
        BlobContainerClient sasContainerClient = new BlobContainerClientBuilder()
                .endpoint("https://" + storageAccountName + ".blob.core.windows.net/" + containerName)
                .sasToken(sasToken)
                .buildClient();

        // List blobs in the container
        for (BlobItem blobItem : sasContainerClient.listBlobs()) {
            System.out.println("\t" + blobItem.getName());
        }

    }

    private static void listFilesWithDefaultAzureCredentials() {
        // <Snippet_CreateServiceClientDAC>
        /*
         * The default credential first checks environment variables for configuration
         * If environment configuration is incomplete, it will try managed identity
         */
        DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();

        // Azure SDK client builders accept the credential as a parameter
        // TODO: Replace <storage-account-name> with your actual storage account name
        String storageAccountName = System.getenv("STORAGE_ACCOUNT");
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint("https://" + storageAccountName + ".blob.core.windows.net/")
//                .endpoint("https://<storage-account-name>.blob.core.windows.net/")
                .credential(defaultCredential)
                .buildClient();
        // </Snippet_CreateServiceClientDAC>

        String containerName = "test";

        // Create the container and return a container client object
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        // </Snippet_CreateContainer>
        // List the blob(s) in the container.
        for (BlobItem blobItem : blobContainerClient.listBlobs()) {
            System.out.println("\t" + blobItem.getName());
        }
    }

    private static void uploadFileToAzure() {
        DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();

        // Azure SDK client builders accept the credential as a parameter
        // TODO: Replace <storage-account-name> with your actual storage account na`me
        String storageAccountName = System.getenv("STORAGE_ACCOUNT");
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint("https://" + storageAccountName + ".blob.core.windows.net/")
//                .endpoint("https://<storage-account-name>.blob.core.windows.net/")
                .credential(defaultCredential)
                .buildClient();

        // Get a reference to a blob
        String containerName = "test";
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient("failed-340-442-3-round.txt");

        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        String localPath = "/home/danilo/Downloads/";
        String fileName = "failed-340-442-3-round.txt";
        // Upload the blob
        blobClient.uploadFromFile(localPath + fileName);
    }

    private static void downloadFileFromAzure() {
        DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();

        // Azure SDK client builders accept the credential as a parameter
        // TODO: Replace <storage-account-name> with your actual storage account na`me
        String storageAccountName = System.getenv("STORAGE_ACCOUNT");
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint("https://" + storageAccountName + ".blob.core.windows.net/")
//                .endpoint("https://<storage-account-name>.blob.core.windows.net/")
                .credential(defaultCredential)
                .buildClient();

        // Get a reference to a blob
        String containerName = "test";
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient("failed-340-442-3-round.txt");

        blobClient.downloadToFile("./test.txt");
    }

}
