package com.example.profilemy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog

class UserAdapter(
    private val context: Context,                         // Used for inflating layouts and showing dialogs/toasts
    private val users: MutableList<Pair<Int, String>>,     // List containing pairs of (userId, userName)
    private val dbHelper: DBHandler                        // Helper class for interacting with the SQLite database
) : BaseAdapter() {

    override fun getCount(): Int = users.size              // Returns total number of user items in the list

    override fun getItem(position: Int): Any = users[position]   // Returns the user object at the given position

    override fun getItemId(position: Int): Long = users[position].first.toLong() // Returns user ID as a Long (needed for adapters)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Reuse an existing view if possible; if not, inflate a new list_item layout
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)

        // Access UI components from the inflated layout
        val userText: TextView = view.findViewById(R.id.itemText) // Displays the username
        val deleteBtn: Button = view.findViewById(R.id.deleteBtn) // Button for deleting a specific user

        // Extract id and name for the user at the current list position
        val (id, name) = users[position]
        userText.text = name                                      // Set the username text

        // Set an OnClickListener for the Delete button
        deleteBtn.setOnClickListener {
            // Create an alert dialog asking for delete confirmation
            AlertDialog.Builder(context)
                .setTitle("Delete User")                          // Title of the confirmation dialog
                .setMessage("Are you sure you want to delete '$name'?") // Confirmation message showing username
                .setPositiveButton("Yes") { _, _ ->               // Executes if the user presses "Yes"
                    if (dbHelper.deleteUser(id)) {                // Call deleteUser() from DBHandler; returns true if successful
                        users.removeAt(position)                  // Remove the user from the current list
                        notifyDataSetChanged()                    // Refresh the ListView to reflect the deletion
                        Toast.makeText(context, "User deleted", Toast.LENGTH_SHORT).show() // Show success toast
                    } else {
                        Toast.makeText(context, "Failed to delete user", Toast.LENGTH_SHORT).show() // Show failure toast
                    }
                }
                .setNegativeButton("No", null)                    // Do nothing if the user presses "No"
                .show()                                           // Display the dialog on the screen
        }

        return view                                               // Return the final prepared view for display in the ListView
    }
}
