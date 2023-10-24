package cloud.spike.azure.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.specialized.BlockBlobClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

//Example taken from https://github.com/Azure-Samples/AzureStorageSnippets

public class BlobUpload {

    public void uploadBlobFromStream(BlobContainerClient blobContainerClient) {
        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient("sampleBlob.txt").getBlockBlobClient();
        String sampleData = "Sample data for blob";
        try (ByteArrayInputStream dataStream = new ByteArrayInputStream(sampleData.getBytes())) {
            blockBlobClient.upload(dataStream, sampleData.length());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // </Snippet_UploadBlobStream>

    // <Snippet_UploadBlobFile>
    public void uploadBlobFromFile(BlobContainerClient blobContainerClient) {
        BlobClient blobClient = blobContainerClient.getBlobClient("sampleBlob.txt");

        try {
            blobClient.uploadFromFile("filepath/local-file.png");
        } catch (UncheckedIOException ex) {
            System.err.printf("Failed to upload from file: %s%n", ex.getMessage());
        }
    }

}
