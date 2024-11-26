document.getElementById('paymentForm').addEventListener('submit', function (e) {
    const fileInput = document.getElementById('paymentProof');
    const file = fileInput.files[0];
    const message = document.getElementById('message');

    message.textContent=''; // clear previous messages

    const allowedTypes = ['image/jpeg', 'image/png', 'application/pdf'];   // checking file type
    if(!allowedTypes.includes(file.type)) {
        message.textContent = 'Please upload a JPEG, PNG or PDF file';
        e.preventDefault();
        return;
    }
    
    const maxSize = 2 * 1024 * 1024;   // max file size = 2 MB
    if(file.size > maxSize) {
        message.textContent = 'File size exceeds 2 MB. Please upload a smaller file.';
        e.preventDefault();
    }

});