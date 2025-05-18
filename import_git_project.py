import os          # For path manipulation and directory handling
import shutil      # For deleting directories (used to remove .git and cleanup)
import subprocess  # For executing shell commands like git

# ================================================
# CONFIGURATION - EDIT THESE BEFORE RUNNING
# ================================================

# URL of the Git repository you want to import FROM
SOURCE_REPO = "https://github.com/kleiderc/structure.git"

# URL of the Git repository you want to push TO
TARGET_REPO = "https://github.com/kleiderc/database.git"

# Temporary local directory to clone into and work from
TEMP_DIR = "temp-source"

# The branch name to push to (commonly 'main' or 'master')
TARGET_BRANCH = "master"

# ================================================


# Defines a helper function run() that executes shell commands.
# cwd allows the command to run inside a specific directory.
# If a command fails, it raises an error so we can stop the script early.
def run(cmd, cwd=None):
    """
    Runs a shell command.
    :param cmd: the shell command string to execute
    :param cwd: optional working directory to run the command in
    """
    print(f"> {cmd}")  # Print the command to console for visibility
    result = subprocess.run(cmd, cwd=cwd, shell=True)  # Run the command in shell

    if result.returncode != 0:  # If the command failed (non-zero exit code)
        raise RuntimeError(f"Command failed: {cmd}")  # Stop the script with an error

        
def main(): # Main function to hold all our logic.

    # Step 1: Clone the source Git repository
    print("Cloning source repository...")
    run(f"git clone {SOURCE_REPO} {TEMP_DIR}") # Clones the source Git repository into a new directory called TEMP_DIR.

    # Step 2: Remove the .git directory inside TEMP_DIR to erase history
    git_folder = os.path.join(TEMP_DIR, ".git")  # Build path to .git folder
    print("Removing Git history...")
    shutil.rmtree(git_folder, ignore_errors=True)  # Recursively delete .git folder.Now the code looks like it has never been committed before.

    # Step 3: Initialize a brand-new Git repository in TEMP_DIR
    print("Initializing new Git repo...")
    run("git init", cwd=TEMP_DIR)  # Runs 'git init' inside TEMP_DIR


    # Step 4: Stage all files and make a fresh commit
    print("Staging and committing files...")
    run("git add .", cwd=TEMP_DIR) # Adds all files to the staging area.
    run('git commit -m "Imported code from source project (without history)"', cwd=TEMP_DIR) # Make first commit.This becomes the first and only commit in the new history.

    # Step 5: Add the new target Git remote
    print("Adding remote origin...")
    run(f"git remote add origin {TARGET_REPO}", cwd=TEMP_DIR)

    # Step 6: Rename the default branch (e.g., from master to main)
    run(f"git branch -M {TARGET_BRANCH}", cwd=TEMP_DIR) # e.g., rename to 'master'

    # Step 7: Push to the remote target repository using --force to overwrite if needed
    print("Pushing to target repo (force)...")
    run(f"git push -u origin {TARGET_BRANCH} --force", cwd=TEMP_DIR) # --force allows overwriting remote history

    # Step 8: Cleanup - delete the temporary folder
    print("Cleaning up temporary directory...")
    shutil.rmtree(TEMP_DIR, ignore_errors=True)

    print("\n✅ Done! Code pushed without history and temp folder removed.")

    
# Python entry point - this ensures the script only runs when executed directly
if __name__ == "__main__":
    try:
        main()  # Run the main logic. Wraps main() in a try/except to catch and display errors nicely.
    except Exception as e:  # Catch any errors that occur and print them nicely
        print(f"\n❌ ERROR: {e}")