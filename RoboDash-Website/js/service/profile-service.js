let profileService;

class ProfileService
{
    loadProfile() {

            const url = `${config.baseUrl}/profile`;

            axios.get(url)
            .then(response => {

                const profile = response.data;

                // Populate form fields
                document.getElementById("firstName").value = profile.firstName || "";
                document.getElementById("lastName").value = profile.lastName || "";
                document.getElementById("phone").value = profile.phone || "";
                document.getElementById("email").value = profile.email || "";
                document.getElementById("address").value = profile.address || "";
                document.getElementById("city").value = profile.city || "";
                document.getElementById("state").value = profile.state || "";
                document.getElementById("zip").value = profile.zip || "";

            })
            .catch(error => {

                console.error("Load profile failed:", error);
                alert("Failed to load profile.");

            });
        }

    updateProfile(profile) {

            const url = `${config.baseUrl}/profile/edit`;

            axios.put(url, profile)
            .then(() => {

                alert("Profile updated successfully!");

            })
            .catch(error => {

                console.error("Save profile failed:", error);
                alert("Save profile failed.");

            });
        }
    }

    function saveProfile() {

        const profile = {
            firstName: document.getElementById("firstName").value,
            lastName: document.getElementById("lastName").value,
            phone: document.getElementById("phone").value,
            email: document.getElementById("email").value,
            address: document.getElementById("address").value,
            city: document.getElementById("city").value,
            state: document.getElementById("state").value,
            zip: document.getElementById("zip").value
        };

        profileService.updateProfile(profile);
    }

    document.addEventListener("DOMContentLoaded", () => {

        profileService = new ProfileService();
        profileService.loadProfile();

    });
