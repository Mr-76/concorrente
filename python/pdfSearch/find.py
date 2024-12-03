import os

def count_pdf_files(base_dir):
    """
    Counts all .pdf files in a directory and its subdirectories.
    
    Args:
        base_dir (str): The base directory to start the search.
    
    Returns:
        int: Total number of .pdf files found.
    """
    pdf_count = 0
    for root, dirs, files in os.walk(base_dir):
        for file in files:
            if file.endswith('.pdf'):
                pdf_count += 1
    return pdf_count

# Example usage:
base_directory = "base_directory"  # Replace with the path to your base directory
total_pdfs = count_pdf_files(base_directory)
print(f"Total .pdf files: {total_pdfs}")

