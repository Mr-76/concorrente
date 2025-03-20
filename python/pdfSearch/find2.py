import os
#call at evety dodler cratea thread to find more files..... recursive function

def find_and_count_pdf_files(base_dir):
    """
    Finds all .pdf files in a directory and its subdirectories, 
    counts them, and stores their paths in a list.
    
    Args:
        base_dir (str): The base directory to start the search.
    
    Returns:
        tuple: A tuple containing:
            - int: Total number of .pdf files found.
            - list: A list of full file paths for the .pdf files.
    """
    pdf_files = []
    for root, dirs, files in os.walk(base_dir):
        for file in files:
            if file.endswith('.pdf'):
                pdf_files.append(os.path.join(root, file))
    return len(pdf_files), pdf_files

# Example usage:
base_directory = "base_directory"  # Replace with the path to your base directory
total_pdfs, pdf_paths = find_and_count_pdf_files(base_directory)

print(f"Total .pdf files: {total_pdfs}")
print("List of .pdf files:")
for path in pdf_paths:
    print(path)
