async function updateStatus() {
    const fileId = document.getElementById("fileId").value;
    const status = document.getElementById("status").value;

    const response = await fetch(`/finance/updateStatus/${fileId}?status=${status}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' }
    });

    const message = await response.text();
    const responseDiv = document.getElementById("responseMessage");

    if (response.ok) {
        responseDiv.innerHTML = `<p style="color: green;">${message}</p>`;
    } else {
        responseDiv.innerHTML = `<p style="color: red;">${message}</p>`;
    }
}