

interface DragItemTouchListener {
    fun onMove(from: Int, to: Int) {}
    fun onMoved(position: Int, direction: Int) {}
}
annotation class ItemTouchDrag
