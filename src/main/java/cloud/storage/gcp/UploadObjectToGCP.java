package cloud.storage.gcp;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadObjectToGCP {

    public static void main(String[] args) throws IOException {
        uploadObject();
    }

    public static void uploadObject() throws IOException {
        // The ID of your GCP project
         String projectId = "docusign-251217";

        // The ID of your GCS bucket
         String bucketName = "test-bucket-danilo";

        // The ID of your GCS object
         String objectName = "tmp/NerDL_c11506ac1bd5.log";

        // The path to your file to upload
         String filePath = "/home/danilo/Downloads/NerDL_c11506ac1bd5.log";

        // Optional: set a generation-match precondition to avoid potential race
        // conditions and data corruptions. The request returns a 412 error if the
        // preconditions are not met.
        // For a target object that does not yet exist, set the DoesNotExist precondition.
        Storage.BlobTargetOption precondition = Storage.BlobTargetOption.doesNotExist();
        // If the destination already exists in your bucket, instead set a generation-match
        // precondition:
        // Storage.BlobTargetOption precondition = Storage.BlobTargetOption.generationMatch();

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] files = Files.readAllBytes(Paths.get(filePath));

        storage.create(blobInfo, files, precondition);

        System.out.println(
                "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
    }


}
