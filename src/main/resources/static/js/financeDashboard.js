document.addEventListener("DOMContentLoaded", () => {
    const viewPaymentsLink = document.getElementById("viewPayments");
    const contentArea = document.getElementById("contentArea");

    viewPaymentsLink.addEventListener("click", (event) => {
        event.preventDefault();

        fetch("http://localhost:8080/finance/submissions")
            .then(response => response.json())
            .then(data => {
                // Clear existing content
                contentArea.innerHTML = `
                    <h2>Submitted Payments</h2>
                    <table class="submissions-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Student Name</th>
                                <th>File</th>
                                <th>Status</th>
                                <th>Update Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                        </tbody>
                    </table>
                `;

                const tableBody = contentArea.querySelector(".submissions-table tbody");

                // Populate table with data
                data.forEach(submission => {
                    const row = document.createElement("tr");

                    row.innerHTML = `
                        <td>${submission.id}</td>
                        <td>${submission.studentName}</td>
                        <td><a href="${submission.fileUrl}" target="_blank">View File</a></td>
                        <td>${submission.status}</td>
                        <td>
                            <select data-id="${submission.id}" class="status-dropdown">
                                <option value="pending" ${submission.status === "pending" ? "selected" : ""}>Pending</option>
                                <option value="approved" ${submission.status === "approved" ? "selected" : ""}>Approved</option>
                                <option value="rejected" ${submission.status === "rejected" ? "selected" : ""}>Rejected</option>
                            </select>
                        </td>
                    `;

                    tableBody.appendChild(row);
                });

                // Add event listeners to status dropdowns
                const dropdowns = contentArea.querySelectorAll(".status-dropdown");
                dropdowns.forEach(dropdown => {
                    dropdown.addEventListener("change", (event) => {
                        const submissionId = event.target.getAttribute("data-id");
                        const newStatus = event.target.value;

                        // Update status on backend
                        fetch(`http://localhost:8080/finance/submissions/${submissionId}`, {
                            method: "PUT",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify({ status: newStatus })
                        })
                            .then(response => {
                                if (response.ok) {
                                    alert("Status updated successfully!");
                                } else {
                                    alert("Failed to update status.");
                                }
                            })
                            .catch(err => {
                                console.error("Error updating status:", err);
                            });
                    });
                });
            })
            .catch(err => {
                console.error("Error fetching submissions:", err);
                contentArea.innerHTML = `<p>Failed to load payment submissions.</p>`;
            });
    });
});