async function updateStatus() {
    const status = document.getElementById('status').value;
    const submissionId = document.getElementById('submissionId').value;  // You should have a submissionId field for this

    try {
        const response = await fetch(`/api/submissions/${submissionId}/status`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ status: status })
        });

        if (response.ok) {
            const updatedSubmission = await response.json();
            alert('Status updated successfully!');
            document.getElementById('currentStatus').textContent = updatedSubmission.status;
        } else {
            alert('Error updating status');
        }
    } catch (err) {
        alert('Failed to update status: ' + err.message);
    }
}

document.addEventListener("DOMContentLoaded", async () => {
    try {
        const response = await fetch('/api/students/details');

        if (response.ok) {
            const student = await response.json();

            // Autofill student details
            document.getElementById('studentName').textContent = student.name;
            document.getElementById('studentId').textContent = student.id;
            document.getElementById('courseName').textContent = student.courseName;
            document.getElementById('email').textContent = student.email;
            document.getElementById('submissionId').value = student.submissionId;
        }
    } catch (err) {
        alert('Error fetching student details: ' + err.message);
    }
});